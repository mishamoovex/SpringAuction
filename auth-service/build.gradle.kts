plugins {
    alias(libs.plugins.spring)
    java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.starter)
    implementation(libs.spring.web)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
