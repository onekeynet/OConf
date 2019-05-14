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