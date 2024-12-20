FROM openjdk:17.0.2-jdk-slim as build
WORKDIR application
COPY . .
RUN ./gradlew installDist

FROM openjdk:17.0.2-jdk-slim
WORKDIR application
RUN apt-get update && apt-get install -y procps
COPY --from=build application/build/install/flexo-sysmlv2/ .
ENTRYPOINT ["./bin/flexo-sysmlv2"]
EXPOSE 8080
