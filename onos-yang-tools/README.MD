## Migrating from Maven to Bazel

1. In order to decouple this project from ONOS/OSGi, we remove all `osgi` 
dependencies and package  `org.onosproject.yang.runtime.helperutils`.