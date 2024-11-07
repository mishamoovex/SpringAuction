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
    implementation(libs.spring.jpa)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.test{
    useJUnitPlatform()
}
