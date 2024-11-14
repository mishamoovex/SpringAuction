plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(platform(libs.spring.cloud.bom))
    implementation(libs.bundles.service.core)
    implementation(libs.webtoken)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
