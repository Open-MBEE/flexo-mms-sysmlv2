# CLAUDE.md

## Project Overview

Flexo MMS SysML v2 Microservice — a REST/HTTP adapter that implements the [SysML v2 API and Services](https://github.com/Systems-Modeling/SysML-v2-API-Services) specification on top of the OpenMBEE Flexo Model Management System (MMS) Layer 1. It translates SysML v2 domain concepts (Projects, Commits, Elements, Branches, Tags, Queries) into RDF graph operations against a triplestore via the MMS Layer 1 service.

## Tech Stack

- **Language:** Kotlin (JVM toolchain 21)
- **Framework:** Ktor 3.x (Netty engine)
- **Serialization:** kotlinx.serialization (JSON, with `@type` class discriminator)
- **RDF/SPARQL:** Apache Jena 6.x (jena-arq, jena-querybuilder)
- **Build:** Gradle 8.10.2 (Kotlin DSL, `build.gradle.kts`)
- **Testing:** Kotest 6.x (StringSpec style) + JUnit 5 + Ktor test host
- **CI:** CircleCI (`.circleci/config.yml`)
- **Code Quality:** SonarCloud + JaCoCo
- **Container:** Docker (Eclipse Temurin 21, multi-stage build)

## Repository Layout

```
src/main/kotlin/org/openmbee/flexo/sysmlv2/
  AppMain.kt            # Ktor application module, routing setup, FlexoConfig
  Configuration.kt      # HSTS and Compression plugin config
  Flexo.kt              # FlexoRequestBuilder, FlexoResponse, RDF parsing helpers
  Http.kt               # HTTP utility helpers
  Namespaces.kt         # RDF namespace constants (SYSMLV2, MMS objects/properties)
  Paths.kt              # Path construction utilities
  RdfPrefixes.kt        # RDF prefix mapping utilities
  apis/                 # API route implementations
    BranchApi.kt
    CommitApi.kt
    DiffMergeApi.kt
    ElementApi.kt
    MetaApi.kt
    ProjectApi.kt
    QueryApi.kt
    RelationshipApi.kt
    TagApi.kt
  infrastructure/       # Auth and serialization helpers
    ApiKeyAuth.kt
    Serializers.kt
  models/               # Kotlin data classes (generated from SysML v2 OpenAPI spec)
    Project.kt, Commit.kt, Branch.kt, Tag.kt, Query.kt,
    Element (DataIdentity, DataVersion, etc.), Constraint types, etc.

src/main/resources/
  application.conf.example  # Ktor + Flexo config template (copy to application.conf)
  logback.xml

src/test/kotlin/org/openmbee/flexo/sysmlv2/
  BranchTest.kt, CommitTest.kt, ElementTest.kt, ProjectTest.kt, QueryTest.kt, TagTest.kt
  ProjectAny.kt
  util/
    Auth.kt             # JWT token generation for tests
    Common.kt            # CommonSpec base class (triplestore reset before each test)
    Environment.kt       # Test environment/config loader
    Helper.kt            # Shared test helpers
    Requests.kt          # HTTP request helpers for tests

src/test/resources/
  application.test.conf  # Ktor config for test runs
  cluster.trig           # Seed RDF dataset (users, groups, ACL policies for layer1)
  docker-compose.yml     # Test stack: Fuseki + layer1-service containers
  test.env               # JWT secrets shared with layer1 container

docker-compose/          # Production/demo Docker Compose configs
  docker-compose.yml           # Full stack: Fuseki + layer1 + sysmlv2
  docker-compose-graphdb.yml   # GraphDB variant
  docker-compose-openmbee.yml  # OpenMBEE cloud variant
  env/                         # Environment variable files
  mount/                       # Seed data mounted into containers

bruno/                   # Bruno API collection for manual testing
```

## Build & Run

```bash
# Prerequisites: JDK 21

# Copy config
cp src/main/resources/application.conf.example src/main/resources/application.conf

# Build and run locally (starts on localhost:8080)
./gradlew run

# Build distribution
./gradlew installDist

# Run via Docker Compose (full stack: Fuseki + Layer1 + SysMLv2)
docker compose -f docker-compose/docker-compose.yml up -d
# SysMLv2 API available at http://localhost:8083

# Build standalone Docker image
docker build --platform linux/amd64 -t openmbee/flexo-sysmlv2:temp .
docker run -p 127.0.0.1:8083:8080 openmbee/flexo-sysmlv2:temp
```

## Testing

Tests are integration tests that run the sysmlv2 Ktor module in-process via `testApplication` and forward calls to a real layer1-service container.

```bash
# 1. Start the test dependency stack (Fuseki triplestore + layer1-service)
docker compose -f src/test/resources/docker-compose.yml up -d

# 2. Run tests
./gradlew test
```

Key test environment variables (with defaults from build.gradle.kts):
- `FLEXO_PROTOCOL` (default: `http`)
- `FLEXO_HOST` (default: `localhost`)
- `FLEXO_PORT` (default: `8080`)
- `FLEXO_SYSMLV2_ORG` (default: `sysmlv2`)
- `FLEXO_MMS_QUERY_URL` (default: `http://localhost:3030/ds/sparql`)
- `FLEXO_MMS_UPDATE_URL` (default: `http://localhost:3030/ds/update`)
- `FLEXO_MMS_GRAPH_STORE_PROTOCOL_URL` (default: `http://localhost:3030/ds/data`)
- `JWT_SECRET` (default: hardcoded test secret)

Each test resets the triplestore (drops all, reloads `cluster.trig`, recreates the sysmlv2 org) via `CommonSpec.beforeEach`.

## Architecture Notes

- **Entry point:** `io.ktor.server.netty.EngineMain` → `AppMain.kt` `Application.module()`
- **Config:** HOCON (`application.conf`) with env var overrides (`FLEXO_HOST`, `FLEXO_PORT`, `FLEXO_PROTOCOL`, `FLEXO_SYSMLV2_ORG`, `FLEXO_AUTH`, `FLEXO_DEFAULT_TIMEOUT`, `BASEPATH`)
- **Request flow:** API routes build `FlexoRequestBuilder` → HTTP call to Layer 1 → parse RDF response with Jena → transform to JSON domain models → respond to client
- **RDF namespaces:** Defined in `Namespaces.kt`. Key prefixes: `sysml:` (SysML v2 vocabulary), `elmt:` (element URNs), `mms:` (MMS ontology), `m-org:` (org context)
- **Models:** Data classes in `models/` package, originally generated from the SysML v2 OpenAPI spec (`openapi.json`), then customized
- **Auth:** Requests to Layer 1 forward the `Authorization` header from the incoming client request, or fall back to `FLEXO_AUTH` env var

## CI/CD

CircleCI pipeline (`.circleci/config.yml`):
1. `build_and_test` — starts test docker-compose stack, builds test Docker image, runs tests
2. `scan` — SonarCloud analysis
3. `deploy_snapshot` — pushes `openmbee/flexo-sysmlv2:{branch}-SNAPSHOT` to DockerHub on `develop`/`release`/`hotfix` branches
4. `deploy_release` — pushes `openmbee/flexo-sysmlv2:{tag}` and `:latest` on version tags (`v*`)

## Common Patterns

- All API implementations are Ktor route extensions registered in `AppMain.kt` routing block under `GlobalFlexoConfig.basePath`
- Use `flexoRequestGet`/`flexoRequestPut`/`flexoRequestPost`/`flexoRequestPatch` extension functions on `RoutingContext` to call Layer 1
- RDF responses are parsed via `FlexoResponse.parseModel` / `FlexoResponse.parseLdp` using Jena
- `FlexoModelHandler.indexOut(iri)` and `indexInv(iri)` extract outgoing/incoming RDF properties for a given resource
- Test specs extend `CommonSpec` (Kotest `StringSpec`) which handles triplestore lifecycle
