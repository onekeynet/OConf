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
    model_id = ODTN_OC_MODEL, ## TODO I don't know the usage for the input model_id.
    visibility = ["//visibility:public"],
)

yang_library(
    name = ODTN_TAPI_MODEL,
    deps = [
            "//onos-yang-tools:yanglib",
        ],
    srcs = glob([
        "repo/tapi/*.yang",
    ]),
    model_id = ODTN_TAPI_MODEL,
    visibility = ["//visibility:public"],
)


java_library(
    name = "yang-registor",
    visibility = ["//visibility:public"],
    deps = [
        "//yang:" + ODTN_OC_MODEL,
        "//yang:" + ODTN_TAPI_MODEL,
    ],
    srcs = glob([
        JAVA_FILES,
    ]),
)