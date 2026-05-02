group = "org.openmbee.flexo.sysmlv2"
version = "0.0.1-ALPHA"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    application
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.serialization") version "2.3.21"
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


    val jenaVersion = "6.0.0"
    implementation("org.apache.jena:jena-arq:${jenaVersion}")
    implementation("org.apache.jena:jena-querybuilder:${jenaVersion}")
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
        // sysmlv2 service URL (the service under test)
        environment("SYSMLV2_BASE_URL", System.getenv("SYSMLV2_BASE_URL") ?: "http://localhost:8083")
        // layer1 service URL (for setup operations)
        environment("FLEXO_LAYER1_BASE_URL", System.getenv("FLEXO_LAYER1_BASE_URL") ?: "http://localhost:8080")
        // login service URL
        environment("FLEXO_LOGIN_URL", System.getenv("FLEXO_LOGIN_URL") ?: "http://localhost:8082/login")
        // credentials
        environment("FLEXO_TEST_USER", System.getenv("FLEXO_TEST_USER") ?: "user01")
        environment("FLEXO_TEST_PASSWORD", System.getenv("FLEXO_TEST_PASSWORD") ?: "password1")
    }
}
