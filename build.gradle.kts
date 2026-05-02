group = "org.openmbee.flexo.sysmlv2"
version = "0.0.1-ALPHA"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

jacoco {
    toolVersion = "0.8.12"
}

plugins {
    application
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.serialization") version "2.3.21"
    jacoco
    id("org.sonarqube") version "6.2.0.5505"
}

sonar {
    properties {
        property("sonar.projectKey", "Open-MBEE_flexo-mms-sysmlv2")
        property("sonar.organization", "openmbee")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    val ktorVersion = "3.4.3"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
   // implementation("io.ktor:ktor-serialization-gson:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
//    implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-server-auto-head-response:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
    implementation("io.ktor:ktor-server-compression:$ktorVersion")
//    implementation("io.ktor:ktor-server-conditional-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
//    implementation("io.ktor:ktor-server-cors:$ktorVersion")
    implementation("io.ktor:ktor-server-default-headers:$ktorVersion")
//    implementation("io.ktor:ktor-server-forwarded-header:$ktorVersion")
//    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-hsts:$ktorVersion")
//    implementation("io.ktor:ktor-server-locations:$ktorVersion")
    implementation("io.ktor:ktor-server-metrics:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-resources:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    val kotestVersion = "6.1.11"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-json-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-ktor:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")

    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:1.5.18")

    val junitVersion = "5.13.1"
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")

    // sign JWTs locally in tests with the same shared secret as the
    // layer1-service container (see src/test/resources/test.env)
    testImplementation("com.auth0:java-jwt:4.5.0")

    val jenaVersion = "6.0.0"
    implementation("org.apache.jena:jena-arq:${jenaVersion}")
    implementation("org.apache.jena:jena-querybuilder:${jenaVersion}")
    // CommonSpec.beforeEach uses RDFConnection to reload cluster.trig via GSP
    testImplementation("org.apache.jena:jena-rdfconnection:${jenaVersion}")
}

tasks.wrapper {
    gradleVersion = "8.10.2"
    distributionType = Wrapper.DistributionType.ALL
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
kotlin {
    jvmToolchain(21)
}
tasks {
    test {
        useJUnitPlatform()
        this.testLogging {
            this.showStandardStreams = true
        }
        // layer1-service running locally (started via
        // src/test/resources/docker-compose.yml). The in-process sysmlv2
        // module under test forwards calls to it.
        environment("FLEXO_PROTOCOL", System.getenv("FLEXO_PROTOCOL") ?: "http")
        environment("FLEXO_HOST", System.getenv("FLEXO_HOST") ?: "localhost")
        environment("FLEXO_PORT", System.getenv("FLEXO_PORT") ?: "8080")
        environment("FLEXO_SYSMLV2_ORG", System.getenv("FLEXO_SYSMLV2_ORG") ?: "sysmlv2")
        // triplestore endpoints used by CommonSpec.beforeEach to drop +
        // reload the seed cluster.trig before each test.
        environment("FLEXO_MMS_QUERY_URL", System.getenv("FLEXO_MMS_QUERY_URL") ?: "http://localhost:3030/ds/sparql")
        environment("FLEXO_MMS_UPDATE_URL", System.getenv("FLEXO_MMS_UPDATE_URL") ?: "http://localhost:3030/ds/update")
        environment("FLEXO_MMS_GRAPH_STORE_PROTOCOL_URL", System.getenv("FLEXO_MMS_GRAPH_STORE_PROTOCOL_URL") ?: "http://localhost:3030/ds/data")
        // JWT shared with layer1 (must match src/test/resources/test.env)
        environment("JWT_DOMAIN", System.getenv("JWT_DOMAIN") ?: "http://flexo-mms-services")
        environment("JWT_AUDIENCE", System.getenv("JWT_AUDIENCE") ?: "flexo-mms-audience")
        environment("JWT_REALM", System.getenv("JWT_REALM") ?: "flexo-mms")
        environment("JWT_SECRET", System.getenv("JWT_SECRET") ?: "thisissomethingreallylong1234567801234567890")
    }
}
tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}
