load("//tools:yang.bzl", "yang_library", "ODTN_OC_MODEL", "ODTN_TAPI_MODEL")
load("//tools:variables.bzl", "JAVA_FILES")

yang_library(
    name = ODTN_OC_MODEL,
    deps = [
        "//onos-yang-tools:yanglib",
    ],
    srcs = glob([
        "repo/openconfig-odtn/*.yang",
    ]),
    model_id = ODTN_OC_MODEL,
    visibility = ["//visibility:public"],
)


## import generated jar from yang files
#java_library(
#    name = "lib-" + ODTN_OC_MODEL,
#    visibility = ["//visibility:public"],
#    srcs = [
#        "//yang:" + ODTN_OC_MODEL,
#    ],
#    deps = [
#      "//onos-yang-tools:yanglib",
#    ],
#)

java_library(
    name = "yang-registor",
    visibility = ["//visibility:public"],
    deps = [
#        "//yang:lib-" + ODTN_OC_MODEL,
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:org_slf4j_slf4j_jdk14",
        "@maven//:com_google_guava_guava",
        "//onos-yang-tools:yanglib",
    ],
    srcs = glob([
        JAVA_FILES,
    ]) + ["//yang:" + ODTN_OC_MODEL],
    resource_jars = ["//yang:" + ODTN_OC_MODEL],
)


# Only for testing.
java_binary(
    name = "yang-test",
    main_class = "org.onekeynet.oconf.yang.registor.OpenConfigOdtnModelRegistrator",
    runtime_deps = ["//yang:yang-registor"],
)