workspace(name = "org_onekeynet_oconf")

# import external Maven dependencies
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
RULES_JVM_EXTERNAL_TAG = "2.0.1"
RULES_JVM_EXTERNAL_SHA = "55e8d3951647ae3dffde22b4f7f8dee11b3f70f3f89424713debd7076197eaca"
http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)
load("@rules_jvm_external//:defs.bzl", "maven_install")

MVN_KRYO = [
    "com.esotericsoftware:kryo:4.0.1",
    "com.esotericsoftware:reflectasm:1.11.7",
    "org.ow2.asm:asm:5.2",
    "com.esotericsoftware:minlog:1.3.0",
    "org.objenesis:objenesis:2.6",
]

MVN_COMMON_TXT = [
    "org.apache.commons:commons-text:1.3",
    "org.apache.commons:commons-lang3:3.7",
]

MVN_SLF4J = [
    "org.slf4j:slf4j-api:1.7.25",
    "org.slf4j:slf4j-jdk14:1.7.25",
]

maven_install(
    artifacts = MVN_KRYO + MVN_COMMON_TXT + MVN_SLF4J + [
        "commons-io:commons-io:2.6",
        "com.google.guava:guava-testlib:22.0",
        "junit:junit:4.12",
        "com.fasterxml.jackson.core:jackson-databind:2.9.5",
        "com.fasterxml.jackson.core:jackson-annotations:2.9.5",
        "org.apache.servicemix.bundles:org.apache.servicemix.bundles.dom4j:1.6.1_5",
        "org.antlr:antlr4-runtime:4.5.3",
        # chaining dependencies
        "com.google.guava:guava:22.0",
        "com.fasterxml.jackson.core:jackson-core:2.9.5",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

# import antlr4 rules in order to implement the function of plugin "antlr4-maven-plugin" in maven.
# TODO Not used yet.
http_archive(
    name = "rules_antlr",
    sha256 = "acd2a25f31aeeea5f58cdb434ae109d03826ae7cc11fe9efce1740102e3f4531",
    strip_prefix = "rules_antlr-0.1.0",
    urls = ["https://github.com/marcohu/rules_antlr/archive/0.1.0.tar.gz"],
)

load("@rules_antlr//antlr:deps.bzl", "antlr_dependencies")
antlr_dependencies()