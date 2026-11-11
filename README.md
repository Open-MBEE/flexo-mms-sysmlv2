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
When it is set but the service cannot be reached, notation requests are answered `502 Bad Gateway`.

Notation output is always the complete element graph of the commit (`page[size]`/`page[after]` are
ignored, no `Link` header), since a page of elements cannot be written as notation on its own. The
`Accept` header is negotiated by quality, each type taking that of its most specific matching range, and
notation is served only when its quality is strictly above JSON's: `*/*`, `text/plain;q=0.8, */*;q=0.8`
or JSON listed beside a notation type keep JSON; `text/plain;q=0, text/*` refuses plain text and, as JSON is not acceptable either, is
answered `406`. A notation `POST`
answers with the JSON commit record even when the client only accepts `text/plain`.

Input: `POST /projects/{projectId}/commits` with one of

- `Content-Type: text/plain` (SysML notation)
- `Content-Type: application/vnd.sysml`
- `Content-Type: application/vnd.kerml`

The body is converted to API JSON elements and committed through the same path as a JSON commit, so
the stored elements have the same ids, `@type`s and properties a JSON client would send. Element ids
are OpenSysML's name-based uuids (`sysml -id uuid`), so posting the same text again updates the same
elements. The `branchId` and `replace` query parameters apply as for JSON; `description` sets the
commit description. Conversion errors are answered `400` with an `Error` whose `description` lists
the diagnostics. Non-error diagnostics do not block the commit: each is returned as one
`X-SysML-Diagnostic` response header line of the form `<severity> at <line>:<col>: <message>` (and
logged), on notation input and output alike; the header is CORS-exposed for browser clients. OpenSysML v0.10.1's `convert` reports only parser
errors, so the header appears once the service emits warnings for a conversion.

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

### Validating commits

With the same service, the model a commit would leave at the branch head can be checked before it
is written. `FLEXO_OPENSYSML_VALIDATE` (HOCON `flexo.opensysml.validate`) sets the level:

- `off` (default): commits are stored as before, nothing is validated.
- `warn`: the commit proceeds; OpenSysML's diagnostics for the resulting model are answered on the
  `200` as one `X-SysML-Diagnostic: <severity> at <line>:<col>: <message>` header each.
- `reject`: a commit whose resulting model has any error-severity diagnostic is refused with
  `422 Unprocessable Entity`, an `Error` whose `description` lists the errors, and the same
  `X-SysML-Diagnostic` headers; nothing is written. A clean model is committed as under `warn`.

`warn` and `reject` need `FLEXO_OPENSYSML_URL`; without it the service refuses to start. The check
applies to JSON and notation commits alike (`replace=true` included): the current head graph with the
commit applied is written as SysML notation and parsed by OpenSysML, and only its diagnostics decide.
An element set OpenSysML cannot write as notation (for instance a reference to an element that does
not exist) is an error too.

Validated commits to one branch are processed one at a time by the service instance (head read, judged
and written under one lock), so the verdict applies to what is written; writers that bypass the service,
and other instances, are not covered.

A request may raise the level with `?validate=warn` or `?validate=reject` on
`POST /projects/{projectId}/commits`; asking for a level below the configured one is answered `400`.
Validation fetches the whole head graph and parses the whole model, so its cost grows with the model.

```
curl -i -X POST "http://localhost:8083/projects/$PROJECT/commits?validate=reject" \
  -H "Authorization: Bearer $TOKEN" -H "Content-Type: text/plain" \
  --data 'package P { part def A :> B; part def B :> A; }'
# HTTP/1.1 422 Unprocessable Entity
# X-SysML-Diagnostic: error at 1:27: A participates in a specialization cycle
# X-SysML-Diagnostic: error at 1:44: B participates in a specialization cycle
```

For the test stack, `src/test/resources/docker-compose.yml` builds `sysml-grpc` from the pinned
OpenSysML release (`src/test/resources/sysml-grpc/Dockerfile`); run the tests with
`FLEXO_OPENSYSML_URL=localhost:50051 ./gradlew test`. Without it the notation tests are skipped.
