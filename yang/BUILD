load("//tools:yang.bzl", "yang_library", "ODTN_OC_MODEL", "ODTN_TAPI_MODEL")
load("//tools:variables.bzl", "JAVA_FILES", "SLF4J")

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

java_library(
    name = "yang-registor",
    visibility = ["//visibility:public"],
    deps = SLF4J + [
        "@maven//:com_google_guava_guava",
        "//onos-yang-tools:yanglib",
    ],
    srcs = glob([
        JAVA_FILES,
    ]) + ["//yang:" + ODTN_OC_MODEL],
    resource_jars = ["//yang:" + ODTN_OC_MODEL],
)