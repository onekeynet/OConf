# This file is based on ONOS project.

ODTN_OC_MODEL = "odtn-oc" # oc - openconfig
ODTN_TAPI_MODEL = "odtn-tapi"

# Implementation of the YANG library rule
def _yang_library_impl(ctx):
    generated_sources = ctx.actions.declare_directory(ctx.attr.model_id)

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
        outputs = [ctx.outputs.all],
        arguments = [
            ctx.outputs.all.path,
            generated_sources.path,
        ],
        command = "jar cf $1 -C $2 schema -C $2 src",
        progress_message = "Assembling YANG Java sources and YANG compiled schema: %s" % ctx.attr.name,
    )
#    print("%s" % ctx.outputs.all.)
#    ctx.actions.run_shell(
#        inputs = [ctx.outputs.all],
#        outputs = ["resources/"+ctx.outputs.all],
#        arguments = [
#            ctx.outputs.all.path,
#            "resources/"+ctx.outputs.all.path,
#        ],
#        command = "cp $1 $2",
#        progress_message = "Copy yang generated jar to resources directory."
#    )

#    ctx.actions.run_shell(
#        inputs = [generated_sources],
#        outputs = [ctx.outputs.srcjar],
#        arguments = [
#            ctx.outputs.srcjar.path,
#            generated_sources.path,
#        ],
#        command = "jar cf $1 -C $2 src",
#        progress_message = "Assembling YANG Java sources: %s" % ctx.attr.name,
#    )
#
#    ctx.actions.run_shell(
#        inputs = [generated_sources],
#        outputs = [ctx.outputs.schema],
#        arguments = [
#            ctx.outputs.schema.path,
#            generated_sources.path,
#        ],
#        command = "jar cf $1 -C $2 schema",
#        progress_message = "Assembling YANG compiled schema: %s" % ctx.attr.name,
#    )

# Rule to generate YANG library from the specified set of YANG models.
yang_library = rule(
    attrs = {
        "deps": attr.label_list(),
        "srcs": attr.label_list(allow_files = True),
        "model_id": attr.string(),
        "_yang_compiler": attr.label(
            executable = True,
            cfg = "host",
            allow_files = True,
            default = Label("//onos-yang-tools:onos_yang_compiler"),
        ),
    },
    outputs = {
#        "srcjar": "%{name}_model.srcjar",
#        "schema": "%{name}_schema.jar",
        "all" : "%{name}_all.jar", # compress generated java source codes and schema files together into the jar file.
    },
    fragments = ["java"],
    implementation = _yang_library_impl,
)