plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(platform(libs.spring.cloud.bom))
    implementation(libs.spring.cloud.eureka.server)
}