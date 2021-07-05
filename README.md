# GrafanaGen

Stands for "Grafana Generator". Surprisingly, aims to generate Grafana dashboards from code.

Made for Grafana v6.2.5 and PromQL, whatever version we use at my main job. 2.*, probably.

## Goals

* Have a library that allow easy (somewhat) reuse of Grafana widgets and PromQL queries.
* Have a way to write PromQL formulae in a more statically typed way.
* Have some user defined templates for widgets and formulae.
* Verify that PromQL labels are allowed where they are.
* Have a limited way of updating the dashboards in the intranet. (Like manually retrieving user auth cookie and dashboards ids and support only updating existing dashboards on multiple hosts)