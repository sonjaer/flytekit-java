#!/usr/bin/env bash

set -euo pipefail

FLYTEIDL_VERSION="0.21.3"

out=src/main/proto

mkdir -p src/main/proto

curl -L "https://github.com/lyft/flyteidl/archive/v${FLYTEIDL_VERSION}.tar.gz" | \
  tar xvzf - \
    --strip-components=2 \
    -C "$out/" \
    "flyteidl-${FLYTEIDL_VERSION}/protos/flyteidl/admin/*.proto" \
    "flyteidl-${FLYTEIDL_VERSION}/protos/flyteidl/core/*.proto" \
    "flyteidl-${FLYTEIDL_VERSION}/protos/flyteidl/event/*.proto" \
    "flyteidl-${FLYTEIDL_VERSION}/protos/flyteidl/service/admin.proto"

# remove trailing whitespace for better OCR
# remove google.api.http options
# remove grpc gateway options
# remove unused import
find "$out" -type f \
    -exec sed -i '' -e "s/ *$//" {} \; \
    -exec sed -i '' -e "/option (google.api.http)/,/};/d" {} \; \
    -exec sed -i '' -e "/option (grpc.gateway.protoc_gen_swagger.options.openapiv2_operation)/,/};/d" {} \; \
    -exec sed -i '' -e "/protoc-gen-swagger\/options\/annotations.proto/d" {} \; \
    -exec sed -i '' -e "/google\/api\/annotations.proto/d" {} \;
