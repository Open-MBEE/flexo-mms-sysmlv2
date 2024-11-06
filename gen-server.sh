#!/bin/bash

OPENAPI_URL="https://raw.githubusercontent.com/Systems-Modeling/SysML-v2-API-Services/refs/heads/master/public/docs/openapi.json"
OPENAPI_PATH="./resources/openapi.json"
OUTPUT_DIR="./gen"
GENERATOR="kotlin-server"

# check if openapi-generator-cli is installed
# brew is for mac and the command is called openapi-generator instead of openapi-generator-cli, fix as needed
if ! command -v openapi-generator-cli &> /dev/null; then
    echo "OpenAPI Generator CLI not found, installing..."
    brew install openapi-generator
fi

# create output directory if it doesn't exist
mkdir -p "$OUTPUT_DIR"

# preprocess the document
curl -sS $OPENAPI_URL | node ./preprocess.js > $OPENAPI_PATH

# confirm the file
ls -lah $OPENAPI_PATH

# run openapi-generator-cli to generate Kotlin stub server
echo "Generating Kotlin stub server from $OPENAPI_PATH..."
openapi-generator-cli generate \
    -g "$GENERATOR" \
    -i "$OPENAPI_PATH" \
    -o "$OUTPUT_DIR" \
    --additional-properties=groupId=org.openmbee.flexo,packageName=org.openmbee.flexo.sysmlv2

# check if the generation was successful
if [ $? -eq 0 ]; then
    echo "Kotlin stub server generated successfully in $OUTPUT_DIR.\n"
else
    echo "Failed to generate Kotlin stub server."
    exit 1
fi

# src is not empty
if [ "$(ls -A ./src)" ]; then
    echo "ERROR: ./src directory is not empty; refusing to overwrite with generated files"
    exit 1
fi

# copy files
cp -r "$OUTPUT_DIR/src/" src
cp -n "$OUTPUT_DIR/Dockerfile" \
    "$OUTPUT_DIR/build.gradle" \
    "$OUTPUT_DIR/settings.gradle" \
    "$OUTPUT_DIR/gradle.properties" \
    .
