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
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.serialization") version "1.9.24"
    id("com.gradleup.shadow") version "8.3.5"
}

dependencies {
    implementation(kotlin("stdlib"))

    val ktorVersion = "2.2.1"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
   // implementation("io.ktor:ktor-serialization-gson:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
//    implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-server-auto-head-response:$ktorVersion")
//    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
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
//    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("io.dropwizard.metrics:metrics-core:4.1.18")

    val junitVersion = "5.10.1"
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.24")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.24")
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")


    val jenaVersion = "4.10.0"
    implementation("org.apache.jena:jena-arq:${jenaVersion}")
}

tasks.wrapper {
    gradleVersion = "8.10.2"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.shadowJar {
    archiveBaseName.set("flexo-sysmlv2")
    archiveClassifier.set("")
    archiveVersion.set("")
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}
tasks {
    test {
        useJUnitPlatform()
    }
}
