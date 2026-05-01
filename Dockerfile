FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR application
COPY . .
RUN ./gradlew installDist

FROM eclipse-temurin:21-jre-jammy
WORKDIR application
RUN apt-get update && apt-get install -y procps && rm -rf /var/lib/apt/lists/*
COPY --from=build application/build/install/org.openmbee.flexo.sysmlv2/ .
EXPOSE 8080
ENTRYPOINT ["./bin/org.openmbee.flexo.sysmlv2"]
