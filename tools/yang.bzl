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

#    ctx.actions.run_shell(
#        inputs = [generated_sources],
#        outputs = [ctx.outputs.all],
#        arguments = [
#            ctx.outputs.all.path,
#            generated_sources.path,
#        ],
#        command = "jar cf $1 -C $2 schema -C $2/src org",
#        progress_message = "Assembling YANG Java sources and YANG compiled schema: %s" % ctx.attr.name,
#    )

    ctx.actions.run_shell(
        inputs = [generated_sources],
        outputs = [ctx.outputs.srcjar],
        arguments = [
            ctx.outputs.srcjar.path,
            generated_sources.path,
        ],
        command = "jar cf $1 -C $2/src org",
        progress_message = "Assembling YANG Java sources: %s" % ctx.attr.name,
    )

    ctx.actions.run_shell(
        inputs = [generated_sources],
        outputs = [ctx.outputs.schema],
        arguments = [
            ctx.outputs.schema.path,
            generated_sources.path,
            ctx.attr.model_id,
        ],
        command = "mv $2/schema $2/$3 && jar cf $1 -C $2 $3", # rename directory schema to the model_id.
        progress_message = "Assembling YANG compiled schema: %s" % ctx.attr.name,
    )

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
        "srcjar": "%{name}_model.srcjar",
        "schema": "%{name}_schema.jar",
    },
    fragments = ["java"],
    implementation = _yang_library_impl,
)