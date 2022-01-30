package com.xobotun.grafanagen.printer.promql.literal

import com.xobotun.grafanagen.model.promql.datatype.*
import com.xobotun.grafanagen.model.promql.literal.PrometheusFloatLiteral
import com.xobotun.grafanagen.model.promql.literal.SimpleFloatLiteral
import com.xobotun.grafanagen.model.promql.literal.StringLiteral
import com.xobotun.grafanagen.model.promql.literal.escape
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SimpleLiteralPrinterOtherTest {

    @Test
    fun canPrintFloatScalar() {
        val result = SimpleLiteralPrinter.print(100.001.toScalar(), listOf(SimpleLiteralPrinter))
        assertEquals("100.001", result)
    }

    @Test
    fun canPrintSimpleFloatLiteral() {
        val result = SimpleLiteralPrinter.print(SimpleFloatLiteral(100.001, 2), listOf(SimpleLiteralPrinter))
        assertEquals("100.00", result)
    }

    @Test
    fun canPrintPrometheusFloatLiteral() {
        val result = SimpleLiteralPrinter.print(PrometheusFloatLiteral("1e+2"), listOf(SimpleLiteralPrinter))
        assertEquals("1e+2", result)
    }

    @Test
    fun canPrintStringScalar() {
        val result = SimpleLiteralPrinter.print(StringScalar("test"), listOf(SimpleLiteralPrinter))
        assertEquals("test", result)
    }

    @Test
    fun canPrintStringLiteral() {
        val result = SimpleLiteralPrinter.print(StringLiteral("test", StringLiteral.EscapeMode.INTERPRETED_SINGLE.char), listOf(SimpleLiteralPrinter))
        assertEquals("'test'", result)
    }

    @Test
    fun canPrintTimeDuration() {
        val result = SimpleLiteralPrinter.print(TimeDuration(years = 10), listOf(SimpleLiteralPrinter))
        assertEquals("10y", result)
    }
}


internal class SimpleLiteralPrinterVectorTest {
    private val metricName = "metricName".toScalar()
    private val labelName1 = "labelName1".toScalar()
    private val labelName2 = "labelName2".toScalar()

    @Test
    fun canPrintSimpleInstant() {
        val result = SimpleLiteralPrinter.print(InstantVector(metricName), listOf(SimpleLiteralPrinter))
        assertEquals("${metricName.value}", result)
    }

    @Test
    fun canPrintInstantWithLabel() {
        val vector = InstantVector(
            metricName,
            listOf(InstantVector.LabelMatcher(labelName1, InstantVector.LabelComparisonOperator.EQUAL, labelName2.escape()))
        )

        val result = SimpleLiteralPrinter.print(vector, listOf(SimpleLiteralPrinter))
        assertEquals("metricName{labelName1=\"labelName2\"}", result)
    }

    @Test
    fun canPrintInstantWithOffset() {
        val vector = InstantVector(
            metricName,
            offsetModifier = TimeDuration(milliseconds = 9999)
        )

        val result = SimpleLiteralPrinter.print(vector, listOf(SimpleLiteralPrinter))
        assertEquals("(metricName offset 9999ms)", result)
    }

    @Test
    fun canPrintInstantWithAt() {
        val vector = InstantVector(
            metricName,
            atModifier = "unix-time".toScalar()
        )

        val result = SimpleLiteralPrinter.print(vector, listOf(SimpleLiteralPrinter))
        assertEquals("(metricName @ unix-time)", result)
    }

    @Test
    fun canPrintInstantWithEverything() {
        val vector = InstantVector(
            metricName,
            listOf(
                InstantVector.LabelMatcher(labelName1, InstantVector.LabelComparisonOperator.EQUAL, labelName2.escape()),
                InstantVector.LabelMatcher(labelName2, InstantVector.LabelComparisonOperator.NOT_EQUAL_REGEX, labelName1.escape())
            ),
            offsetModifier = TimeDuration(isNegated = true, minutes = 10),
            atModifier = "start()".toScalar(),
        )

        val result = SimpleLiteralPrinter.print(vector, listOf(SimpleLiteralPrinter))
        assertEquals("(metricName{labelName1=\"labelName2\", labelName2!~\"labelName1\"} offset -10m @ start())", result)
    }

    @Test
    fun canPrintRangeWithEverything() {
        val vector = RangeVector(
            InstantVector(
                metricName,
                listOf(
                    InstantVector.LabelMatcher(labelName1, InstantVector.LabelComparisonOperator.NOT_EQUAL, labelName2.escape()),
                    InstantVector.LabelMatcher(labelName2, InstantVector.LabelComparisonOperator.EQUAL_REGEX, labelName1.escape())
                ),
                offsetModifier = TimeDuration(isNegated = true, minutes = 10),
                atModifier = "start()".toScalar(),
            ),
            timeDuration = TimeDuration(minutes = 5)
        )

        val result = SimpleLiteralPrinter.print(vector, listOf(SimpleLiteralPrinter))
        assertEquals("(metricName{labelName1!=\"labelName2\", labelName2=~\"labelName1\"}[5m] offset -10m @ start())", result)
    }
}
