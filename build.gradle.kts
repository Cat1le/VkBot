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
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
