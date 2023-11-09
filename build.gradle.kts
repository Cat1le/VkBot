import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "io.evil"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:6.0.13")
    implementation("com.vk.api:sdk:1.0.15")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.asynchttpclient:async-http-client:3.0.0.Beta3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
