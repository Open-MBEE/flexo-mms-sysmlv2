# Flexo MMS SysML v2 Microservice

REST/HTTP platform specific model (PSM) for the Systems Modeling API and Services

## Requires
JDK 21 for development or running without docker

## Run

```
cp src/main/resources/application.conf.example to src/main/resources/application.conf

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
