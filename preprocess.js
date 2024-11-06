#!/usr/bin/env node
(async function run() {
    // read and build string from stdin stream
    let sx_json = '';
    for await(const s_chunk of process.stdin) {
        sx_json += s_chunk;
    }

    // parse as JSON
    const g_doc = JSON.parse(sx_json);

    // replace the $id URLs with their basename
    for (const [si_schema, g_schema] of Object.entries(g_doc.components.schemas)) {
        g_schema.$id = g_schema.$id.replace(/^.*\/([^/]+)$/, '$1')
    }

    // print the modified document to stdout
    console.log(JSON.stringify(g_doc, null, '  '));
})();
