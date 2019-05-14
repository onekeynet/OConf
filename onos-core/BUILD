load("//tools:variables.bzl", "JAVA_FILES", "KRYO")

java_library(
    name = "onos-core",
    deps = KRYO + [
        "@maven//:com_google_guava_guava",
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:org_slf4j_slf4j_jdk14",
        "@maven//:org_apache_commons_commons_lang3",
    ],
    srcs = glob([
        "api/" + JAVA_FILES,
    ]),
    visibility = ["//visibility:public"],
)