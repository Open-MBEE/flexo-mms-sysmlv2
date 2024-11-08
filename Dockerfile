FROM openjdk:8-jre-alpine

COPY ./build/libs/flexo-sysmlv2.jar /root/flexo-sysmlv2.jar

WORKDIR /root

CMD ["java", "-server", "-Xms4g", "-Xmx4g", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "flexo-sysmlv2.jar"]
