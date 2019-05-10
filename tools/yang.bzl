# Implementation of the YANG library rule
def _yang_library_impl(ctx):
    generated_sources = ctx.actions.declare_directory("generated-sources")

    arguments = [
        ctx.attr.model_id,
        generated_sources.path,
    ]
    inputs = []

    for dep in ctx.files.deps:
        arguments += ["-d", dep.path]
        inputs += [dep]

    for source in ctx.files.srcs:
        arguments += [source.path]
        inputs += [source]

    ctx.actions.run(
        inputs = inputs,
        outputs = [generated_sources],
        arguments = arguments,
        progress_message = "Compiling YANG library: %s" % ctx.attr.name,
        executable = ctx.executable._yang_compiler,
    )

    ctx.actions.run_shell(
        inputs = [generated_sources],
        outputs = [ctx.outputs.srcjar],
        arguments = [
            ctx.outputs.srcjar.path,
            generated_sources.path,
        ],
        command = "jar cf $1 -C $2 src",
        progress_message = "Assembling YANG Java sources: %s" % ctx.attr.name,
    )

    ctx.actions.run_shell(
        inputs = [generated_sources],
        outputs = [ctx.outputs.schema],
        arguments = [
            ctx.outputs.schema.path,
            generated_sources.path,
        ],
        command = "jar cf $1 -C $2 schema",
        progress_message = "Assembling YANG compiled schema: %s" % ctx.attr.name,
    )

# Rule to generate YANG library from the specified set of YANG models.
_yang_library = rule(
    attrs = {
        "deps": attr.label_list(),
        "srcs": attr.label_list(allow_files = True),
        "model_id": attr.string(),
        "_yang_compiler": attr.label(
            executable = True,
            cfg = "host",
            allow_files = True,
            default = Label("//onos-yang-tools/bazel:onos_yang_compiler"),
        ),
    },
    outputs = {
        "srcjar": "model.srcjar",
        "schema": "schema.jar",
    },
    fragments = ["java"],
    implementation = _yang_library_impl,
)

def yang_library(
        name = None,
        deps = None,
        yang_srcs = None,
        java_srcs = None,
        custom_registrator = False,
        visibility = ["//visibility:public"]):
    if name == None:
        name = "onos-" + native.package_name().replace("/", "-")
    if yang_srcs == None:
        yang_srcs = native.glob(["src/main/yang/**/*.yang"])
    if java_srcs == None:
        java_srcs = native.glob(["src/main/java/**/*.java"])
    if deps == None:
        deps = []

    deps += CORE_DEPS + ONOS_YANG + [
        "@onos_yang_runtime//jar",
        "//apps/yang:onos-apps-yang",
    ]

    # Generate the Java sources from YANG model
    _yang_library(name = name + "-generate", model_id = name, deps = deps, srcs = yang_srcs)

    srcs = [name + "-generate"]

    if len(java_srcs):
        srcs += [name + "-srcjar"]
        native.genrule(
            name = name + "-srcjar",
            srcs = java_srcs,
            outs = [name + ".srcjar"],
            cmd = "jar cf $(location %s.srcjar) $(SRCS)" % name,
        )

    if not custom_registrator:
        srcs += [name + "-registrator"]
        native.genrule(
            name = name + "-registrator",
            outs = [REGISTRATOR_FILE],
            cmd = "echo '%s' > $(location %s)" % (REGISTRATOR, REGISTRATOR_FILE),
        )

    # Produce a Java library from the generated Java sources
    osgi_jar(
        name = name,
        srcs = srcs,
        resource_jars = [name + "-generate"],
        deps = deps,
        visibility = ["//visibility:public"],
        suppress_errorprone = True,
        suppress_checkstyle = True,
        suppress_javadocs = True,
    )

def yang_model(
        name = None,
        app_name = None,
        title = None,
        description = None,
        url = "http://onosproject.org/",
        custom_registrator = False,
        deps = None,
        yang_srcs = None,
        java_srcs = None,
        required_apps = [],
        visibility = ["//visibility:public"]):
    if name == None:
        name = "onos-" + native.package_name().replace("/", "-")

    yang_library(
        name = name,
        deps = deps,
        yang_srcs = yang_srcs,
        java_srcs = java_srcs,
        custom_registrator = custom_registrator,
        visibility = ["//visibility:public"],
    )

    onos_app(
        app_name = app_name,
        title = title,
        description = description,
        feature_name = name,
        version = ONOS_VERSION,
        url = url,
        category = "Models",
        included_bundles = [name],
        required_apps = required_apps + ["org.onosproject.yang"],
        visibility = ["//visibility:public"],
    )

