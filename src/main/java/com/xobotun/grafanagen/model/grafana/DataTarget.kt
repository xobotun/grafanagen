package com.xobotun.grafanagen.model.grafana

/**
 * A class that holds an expression that shows the data
 * along with some metadata.
 *
 * TODO: find this in the sources and plan a class hierarchy
 */
data class DataTarget(
    val refId: String = "A",
    val scenarioId: String = "random_walk",
    val stringInput: String = ""
)

//           "expr": "sum(increase(infosys_abel_time_spent_processing_sum{topic=~\"$topic_list\",time_order=\"0\",service=~\"$consul_nodes\"}[$__interval])) / sum(increase(infosys_abel_time_spent_processing_count{topic=~\"$topic_list\",time_order=\"0\",service=~\"$consul_nodes\"}[$__interval]))",
//          "format": "time_series",
//          "intervalFactor": 1,
//          "refId": "A"

//"refId": "A",
//"scenarioId": "random_walk",
//"stringInput": ""

//           "expr": "sum(increase(infosys_abel_streams_message_type_determined_total{signature=~\"$valid_message_types\",topic=~\"$topic_list\",service=~\"$consul_nodes\"}[$__range]))",
//          "format": "time_series",
//          "instant": false,
//          "intervalFactor": 1,
//          "legendFormat": "{{topic}}",
//          "refId": "A"
