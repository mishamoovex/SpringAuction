plugins {
    alias(libs.plugins.spring)
    java
}

dependencies {
    implementation(libs.spring.starter)
    implementation(libs.spring.web)
}
