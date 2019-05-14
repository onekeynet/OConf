JAVA_FILES = "src/main/java/**/*.java"
RESOURCES_FILES = "src/main/resources/**"
OCONF_VERSION = "0.0.1-SNAPSHOT"
OCONF_ARTIFACT_BASE = "oconf-"
OCONF_GROUP_ID = "org.onekeynet.oconf"
OCONF_ORIGIN = "OneKeyNet Community"
APP_PREFIX = OCONF_GROUP_ID + "."

KRYO = [
        "@maven//:com_esotericsoftware_kryo",
        "@maven//:com_esotericsoftware_reflectasm",
        "@maven//:org_ow2_asm_asm",
        "@maven//:com_esotericsoftware_minlog",
        "@maven//:org_objenesis_objenesis",
]

SLF4J = [
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:org_slf4j_slf4j_jdk14",
]

COMMON_TXT = [
    "@maven//:org_apache_commons_commons_text",
    "@maven//:org_apache_commons_commons_lang3",
]

SSHD = [
    "@maven//:org_apache_sshd_sshd_core",
    "@maven//:org_apache_mina_mina_core",
]