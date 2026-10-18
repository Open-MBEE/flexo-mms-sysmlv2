# Flexo MMS SysML v2 Microservice

REST/HTTP platform specific model (PSM) for the Systems Modeling API and Services

## Requires
JDK 21 for development or running without docker

## Run

```
cp src/main/resources/application.conf.example src/main/resources/application.conf

./gradlew run
```
This stands up a server at localhost:8080

## Running in docker

Via Docker Compose that starts the prerequisite services:

```
docker compose -f ./docker-compose/docker-compose.yml up -d
```

To run a single container for the SysML v2 server (other components need to be started separately, this also binds to a loopback address to avoid unintentionally exposing the service externally):

```
docker build --platform linux/amd64 -t openmbee/flexo-sysmlv2:temp .
docker run -p 127.0.0.1:8083:8080 openmbee/flexo-sysmlv2:temp
```
You should then be able to access the `http://localhost:8083/projects` endpoint, for example.

## Textual notation (optional)

The service can read and write SysML v2 textual notation by delegating to an
[OpenSysML](https://github.com/Open-MBEE/OpenSysML) `sysml-grpc` service through its Java client.
Set `FLEXO_OPENSYSML_URL` (HOCON `flexo.opensysml.url`) to the `host:port` of a running `sysml-grpc`,
for example `localhost:50051`. Leave it empty (the default) and the feature is off: the JSON routes
behave as before, notation input is answered `415 Unsupported Media Type` and notation output
`406 Not Acceptable`.

Input: `POST /projects/{projectId}/commits` with one of

- `Content-Type: text/plain` (SysML notation)
- `Content-Type: application/vnd.sysml`
- `Content-Type: application/vnd.kerml`

The body is converted to API JSON elements and committed through the same path as a JSON commit, so
the stored elements have the same ids, `@type`s and properties a JSON client would send. Element ids
are OpenSysML's name-based uuids (`sysml -id uuid`), so posting the same text again updates the same
elements. The `branchId` and `replace` query parameters apply as for JSON; `description` sets the
commit description. Conversion errors are answered `400` with an `Error` whose `description` lists
the diagnostics; warnings are logged and the commit proceeds.

```
curl -X POST "http://localhost:8083/projects/$PROJECT/commits" \
  -H "Authorization: Bearer $TOKEN" -H "Content-Type: text/plain" \
  --data 'package P { part def A; part a : A; }'
```

Output: `GET /projects/{projectId}/commits/{commitId}/elements` with `Accept: text/plain`,
`application/vnd.sysml` or `application/vnd.kerml` returns the commit's elements as notation.

```
curl "http://localhost:8083/projects/$PROJECT/commits/$COMMIT/elements" \
  -H "Authorization: Bearer $TOKEN" -H "Accept: text/plain"
```

For the test stack, `src/test/resources/docker-compose.yml` builds `sysml-grpc` from the pinned
OpenSysML release (`src/test/resources/sysml-grpc/Dockerfile`); run the tests with
`FLEXO_OPENSYSML_URL=localhost:50051 ./gradlew test`. Without it the notation tests are skipped.
