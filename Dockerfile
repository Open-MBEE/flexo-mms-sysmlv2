FROM openjdk:8-jre-alpine

COPY ./build/libs/flexo-mms-sysmlv2.jar /root/flexo-mms-sysmlv2.jar

WORKDIR /root

CMD ["java", "-server", "-Xms4g", "-Xmx4g", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "flexo-mms-sysmlv2.jar"]
