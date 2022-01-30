package com.xobotun.grafanagen.printer.promql.oneline

import com.xobotun.grafanagen.model.promql.datatype.InstantVector
import com.xobotun.grafanagen.model.promql.datatype.InstantVectorProvider
import com.xobotun.grafanagen.model.promql.datatype.toScalar
import com.xobotun.grafanagen.model.promql.function.PrometheusFunction
import com.xobotun.grafanagen.model.promql.function.PrometheusFunctionCall
import com.xobotun.grafanagen.model.promql.literal.escape
import com.xobotun.grafanagen.model.promql.operator.*
import com.xobotun.grafanagen.printer.promql.Printer
import com.xobotun.grafanagen.printer.promql.literal.SimpleLiteralPrinter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class OneLineCallsitePrinterTest {
    private val printers = listOf(SimpleLiteralPrinter, OneLineCallsitePrinter)
    private val simpleInstantVector = InstantVector("simpleVector".toScalar())

    @Test
    fun canPrintTrivialFunction() {
        val callsite = PrometheusFunctionCall(
            PrometheusFunction.PI,
            emptyList()
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(pi())", result)
    }

    @Test
    fun canPrintSimpleFunction() {
        val callsite = PrometheusFunctionCall(
            PrometheusFunction.DAY_OF_WEEK,
            listOf("wrong_argument".toScalar())
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(day_of_week(wrong_argument))", result)
    }

    @Test
    fun canPrintBiScalarOperator() {
        val callsite = PrometheusBiScalarOperatorCall(
            PrometheusBiScalarOperator.ADDITION,
            10.toScalar(),
            11.toScalar()
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(10.0 + 11.0)", result)
    }

    @Test
    fun canPrintScalarVectorOperator() {
        val callsite = PrometheusScalarInstantVectorOperatorCall(
            PrometheusScalarInstantVectorOperator.MODULO,
            isFlipped = false,
            yieldBoolean = false,
            simpleInstantVector,
            9000.toScalar()
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(simpleVector % 9000.0)", result)
    }

    @Test
    fun canPrintHarderScalarVectorOperator() {
        val callsite = PrometheusScalarInstantVectorOperatorCall(
            PrometheusScalarInstantVectorOperator.EQUAL,
            isFlipped = true,
            yieldBoolean = true,
            simpleInstantVector,
            9000.toScalar()
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(9000.0 == bool simpleVector)", result)
    }

    @Test
    fun canPrintSimpleBiVectorOperator() {
        val callsite = PrometheusBiInstantVectorOperatorCall(
            PrometheusBiInstantVectorOperator.ATAN_2,
            yieldBoolean = false,
            leftSide = simpleInstantVector,
            rightSide = InstantVector("another".toScalar()),
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(simpleVector atan2 another)", result)
    }

    @Test
    fun canPrintMatcherBiVectorOperator() {
        val callsite = PrometheusBiInstantVectorOperatorCall(
            PrometheusBiInstantVectorOperator.GREATER_THAN,
            yieldBoolean = false,
            leftSide = simpleInstantVector,
            rightSide = InstantVector("another".toScalar()),
            labelMatcher = PrometheusBiInstantVectorOperatorCall.LabelMatcher(
                type = PrometheusBiInstantVectorOperator.LabelMatcherMode.ON,
                labels = listOf("any".toScalar().escape())
            )
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(simpleVector > on(\"any\") another)", result)
    }

    @Test
    fun canPrintGrouperBiVectorOperator() {
        val callsite = PrometheusBiInstantVectorOperatorCall(
            PrometheusBiInstantVectorOperator.GREATER_THAN,
            yieldBoolean = false,
            leftSide = simpleInstantVector,
            rightSide = InstantVector("another".toScalar()),
            labelGrouper = PrometheusBiInstantVectorOperatorCall.LabelGrouper(
                type = PrometheusBiInstantVectorOperator.LabelGrouperMode.GROUP_LEFT,
                labels = listOf("any".toScalar().escape())
            )
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(simpleVector > group_left(\"any\") another)", result)
    }

    @Test
    fun canPrintFullBiVectorOperator() {
        val callsite = PrometheusBiInstantVectorOperatorCall(
            PrometheusBiInstantVectorOperator.COMPLEMENT,
            yieldBoolean = true,
            leftSide = simpleInstantVector,
            rightSide = InstantVector("another".toScalar()),
            labelMatcher = PrometheusBiInstantVectorOperatorCall.LabelMatcher(
                type = PrometheusBiInstantVectorOperator.LabelMatcherMode.IGNORING,
                labels = listOf("any".toScalar().escape(), "secondary".toScalar().escape()),
            ),
            labelGrouper = PrometheusBiInstantVectorOperatorCall.LabelGrouper(
                type = PrometheusBiInstantVectorOperator.LabelGrouperMode.GROUP_RIGHT,
                labels = listOf("random".toScalar().escape())
            )
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(simpleVector unless bool ignoring(\"any\", \"secondary\") group_right(\"random\") another)", result)
    }

    @Test
    fun canPrintSimpleAggregareOperator() {
        val callsite = PrometheusAggregateOperatorCall(
            PrometheusAggregateOperator.COUNT,
            simpleInstantVector,
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(count(simpleVector))", result)
    }

    @Test
    fun canPrintAggregareOperator() {
        val callsite = PrometheusAggregateOperatorCall(
            PrometheusAggregateOperator.COUNT,
            simpleInstantVector,
            PrometheusAggregateOperatorCall.LabelAggregator(
                type = PrometheusAggregateOperator.LabelAggregateMode.WITHOUT,
                labels = listOf("random".toScalar().escape())
            )
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(count without(\"random\") (simpleVector))", result)
    }

    @Test
    fun canPrintParametrizedAggregareOperator() {
        val callsite = PrometheusParametrizedAggregateOperatorCall(
            PrometheusParametrizedAggregateOperator.BOTTOMK,
            simpleInstantVector,
            10.toScalar(),
            PrometheusAggregateOperatorCall.LabelAggregator(
                type = PrometheusAggregateOperator.LabelAggregateMode.BY,
                labels = listOf("incontinentia".toScalar().escape())
            )
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(bottomk by(\"incontinentia\") (10.0, simpleVector))", result)
    }

    @Test
    fun canPrintSimpleRecursion() {
        val internal = PrometheusScalarInstantVectorOperatorCall(
            operator = PrometheusScalarInstantVectorOperator.MULTIPLICATION,
            isFlipped = false,
            yieldBoolean = false,
            leftSide = simpleInstantVector,
            rightSide = 10.toScalar()
        )

        val callsite = PrometheusParametrizedAggregateOperatorCall(
            PrometheusParametrizedAggregateOperator.BOTTOMK,
            internal as InstantVectorProvider,
            10.toScalar(),
            PrometheusAggregateOperatorCall.LabelAggregator(
                type = PrometheusAggregateOperator.LabelAggregateMode.BY,
                labels = listOf("incontinentia".toScalar().escape())
            )
        )

        val result = Printer.print(callsite, printers)

        assertEquals("(bottomk by(\"incontinentia\") (10.0, (simpleVector * 10.0)))", result)
    }

    @Test
    fun throwsExceptionOnBadConfiguration() {
        val callsite = PrometheusAggregateOperatorCall(
            PrometheusAggregateOperator.COUNT,
            simpleInstantVector,
        )

        assertThrows(IllegalArgumentException::class.java) { Printer.print(callsite, listOf(OneLineCallsitePrinter)) }
    }

}

