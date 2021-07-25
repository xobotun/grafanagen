package com.xobotun.grafanagen.model.grafana

/**
 * Template variables along with their stored values and some metadata.
 *
 * Documentation example:
 * ```
 * "templating": {
 *   "enable": true,
 *   "list": [
 *     {
 *       "allFormat": "wildcard",
 *       "current": {
 *         "tags": [],
 *         "text": "prod",
 *         "value": "prod"
 *       },
 *       "datasource": null,
 *       "includeAll": true,
 *       "name": "env",
 *       "options": [
 *         {
 *           "selected": false,
 *           "text": "All",
 *           "value": "*"
 *         },
 *         {
 *           "selected": false,
 *           "text": "stage",
 *           "value": "stage"
 *         },
 *         {
 *           "selected": false,
 *           "text": "test",
 *           "value": "test"
 *         }
 *       ],
 *       "query": "tag_values(cpu.utilization.average,env)",
 *       "refresh": false,
 *       "refresh": false,
 *       "type": "query"
 *     },
 *     {
 *       "allFormat": "wildcard",
 *       "current": {
 *         "text": "apache",
 *         "value": "apache"
 *       },
 *       "datasource": null,
 *       "includeAll": false,
 *       "multi": false,
 *       "multiFormat": "glob",
 *       "name": "app",
 *       "options": [
 *         {
 *           "selected": true,
 *           "text": "tomcat",
 *           "value": "tomcat"
 *         },
 *         {
 *           "selected": false,
 *           "text": "cassandra",
 *           "value": "cassandra"
 *         }
 *       ],
 *       "query": "tag_values(cpu.utilization.average,app)",
 *       "refresh": false,
 *       "regex": "",
 *       "type": "query"
 *     }
 *   ]
 * }
 * ```
 */
data class Templating(
    /** Whether the templating is enabled or not. */
    val enable: Boolean,
    /** List of variables */
    val list: List<Any>,
)
