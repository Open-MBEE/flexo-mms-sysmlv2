# OpenSysML sysml-grpc for the test stack, from the pinned GitHub release
# (no published OpenSysML image yet). The binary is checked against its
# .sha256 sidecar and against the digest pinned here.
FROM debian:bookworm-slim

ARG OPENSYSML_VERSION=v0.10.1
ARG OPENSYSML_SHA256=1469eb7516a439fc372bc99812bf8bb5b4d2fb1b7279af29b2f055d6edbd1134

RUN apt-get update \
 && apt-get install -y --no-install-recommends ca-certificates curl \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /opt/opensysml
RUN curl -fsSLO "https://github.com/Open-MBEE/OpenSysML/releases/download/${OPENSYSML_VERSION}/sysml-grpc-linux-amd64" \
 && curl -fsSLO "https://github.com/Open-MBEE/OpenSysML/releases/download/${OPENSYSML_VERSION}/sysml-grpc-linux-amd64.sha256" \
 && sha256sum -c sysml-grpc-linux-amd64.sha256 \
 && echo "${OPENSYSML_SHA256}  sysml-grpc-linux-amd64" | sha256sum -c - \
 && mv sysml-grpc-linux-amd64 sysml-grpc \
 && chmod +x sysml-grpc

EXPOSE 50051
HEALTHCHECK --interval=5s --timeout=3s --retries=10 CMD curl -fs http://localhost:50051/health || exit 1
ENTRYPOINT ["/opt/opensysml/sysml-grpc", "-port", "50051", "-health-port", "0"]
