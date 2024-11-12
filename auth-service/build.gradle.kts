plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(libs.spring.starter)
    implementation(libs.spring.web)
}
