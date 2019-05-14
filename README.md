# OConf
yang-enabled NetConf server

1. The directory `onos-yang-tools` are based on ONOS's project.



## Problems
* Testing for `onos-yang-tools` via Bazel.
* Antlr usage in Bazel to replace antlr4 plugin in maven.
* RPC is not supported.
* No Yang Validator
* We remove the Event, its Listener, and relative Adaptor class to avoid more importing.
* We remove the Delegate mechanism in ONOS Store, because the storage here is not distributed.
* JAXB configuration in Netconf


## ToDoList
* please reference https://github.com/dana-i2cat/netconf-server to implement Netconf 
connection and message dispatching.