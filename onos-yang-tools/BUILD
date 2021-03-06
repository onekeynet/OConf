load("//tools:variables.bzl", "JAVA_FILES", "RESOURCES_FILES")


# Usage:
#   CMD modelId outputDir [-d dependencyJar1 -d dependencyJar2...] inputYangFile1 inputYangFile2...
# Example:
#   ./bazel-bin/onos-yang-tools/onos_yang_compiler test-odtn test yang/openconfig-odtn/*.yang
java_binary(
    name = "onos_yang_compiler",
    main_class = "org.onosproject.yang.compiler.main.YangCompilerMain",
    visibility = ["//visibility:public"],
    runtime_deps = [
        "//onos-yang-tools:yanglib",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)

java_library(
    name = "yanglib",
    srcs = glob([
        "compiler/api/" + JAVA_FILES,
        "compiler/base/datamodel/" + JAVA_FILES,
        "compiler/base/linker/" + JAVA_FILES,
        "compiler/base/parser/" + JAVA_FILES,
        "compiler/base/tool/" + JAVA_FILES,
        "compiler/base/translator/" + JAVA_FILES,
        "compiler/base/utils/" + JAVA_FILES,
        "compiler/plugin/main/" + JAVA_FILES,

        "model/" + JAVA_FILES,
        "runtime/" + JAVA_FILES,
        "serializers/json/" + JAVA_FILES,
        "serializers/utils/" + JAVA_FILES,
        "serializers/xml/" + JAVA_FILES,
    ]),
    resources = glob([
        "compiler/api/" + RESOURCES_FILES,
        "compiler/base/datamodel/" + RESOURCES_FILES,
        "compiler/base/linker/" + RESOURCES_FILES,
        "compiler/base/parser/" + RESOURCES_FILES,
        "compiler/base/tool/" + RESOURCES_FILES,
        "compiler/base/translator/" + RESOURCES_FILES,
        "compiler/base/utils/" + RESOURCES_FILES,

        "model/" + RESOURCES_FILES,
        "runtime/" + RESOURCES_FILES,
        "serializers/json/" + RESOURCES_FILES,
        "serializers/utils/" + RESOURCES_FILES,
        "serializers/xml/" + RESOURCES_FILES,
    ]),
    deps = [
        "@maven//:commons_io_commons_io",
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:com_google_guava_guava_testlib",
        "@maven//:junit_junit",
        "@maven//:com_fasterxml_jackson_core_jackson_databind",
        "@maven//:com_fasterxml_jackson_core_jackson_annotations",
        "@maven//:org_apache_servicemix_bundles_org_apache_servicemix_bundles_dom4j",
        "@maven//:org_antlr_antlr4_runtime",

        "@maven//:com_google_guava_guava",
        "@maven//:com_fasterxml_jackson_core_jackson_core",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
    visibility = ["//visibility:public"],
)
