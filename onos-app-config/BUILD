load("//tools:variables.bzl", "JAVA_FILES")

KRYO = [
    "@maven//:com_esotericsoftware_kryo",
    "@maven//:com_esotericsoftware_reflectasm",
    "@maven//:org_ow2_asm_asm",
    "@maven//:com_esotericsoftware_minlog",
    "@maven//:org_objenesis_objenesis",
]

COMMON_TXT = [
    "@maven//:org_apache_commons_commons_text",
    "@maven//:org_apache_commons_commons_lang3",
]

DEPS = KRYO + COMMON_TXT + [
    "//onos-yang-tools:yanglib",
    "@maven//:org_slf4j_slf4j_api",
    "@maven//:org_slf4j_slf4j_jdk14",
    "@maven//:com_google_guava_guava",
    "//onos-core:onos-core",
]

java_library(
    name = "dynamic-config-store",
    visibility = ["//visibility:public"],
    srcs = ["src/main/java/org/onosproject/d/config/DataNodes.java"],
    deps = DEPS,
)


#APPS = [
#    "org.onosproject.yang",
#]
#
#COMPILE_DEPS = CORE_DEPS + KRYO + CLI + ONOS_YANG + [
#    "//core/store/serializers:onos-core-serializers",
#    "@commons_text//jar",
#]
#
#osgi_jar_with_tests(
#    deps = COMPILE_DEPS,
#)
#
#onos_app(
#    category = "Utility",
#    description = "Provides means to track and distribute service and device configuration data " +
#                  "throughout the ONOS cluster. It works with the YANG runtime to ensure that the tracked " +
#                  "data adheres to the registered YANG models. Applications can traverse and access this " +
#                  "data which is organized in a logically unified tree.<p/>(Under development)",
#    required_apps = APPS,
#    title = "Dynamic Configuration",
#    url = "http://onosproject.org",
#)
