#osgi_jar_with_tests(
#    deps = CORE_DEPS + JACKSON + JAXB,
#)
load("//tools:variables.bzl", "JAVA_FILES", "COMMON_TXT", "SLF4J", "SSHD")

java_library(
    name = "netconf-server",
    srcs = glob([
#        "src/main/java/org/onosproject/netconf/rpc/*.java",
#        JAVA_FILES,
        "src/main/java/org/onosproject/netconf/server/*.java",
    ]),
    deps = COMMON_TXT + SLF4J + SSHD + [
        "@maven//:com_google_guava_guava",
    ],
    visibility = ["//visibility:public"],
)

java_binary(
    name = "server",
    runtime_deps = [
        ":netconf-server",
    ],
    main_class = "org.onosproject.netconf.server.NetconfMinaServer",
    visibility = ["//visibility:public"],
)