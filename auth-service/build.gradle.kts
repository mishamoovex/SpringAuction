plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(project(":core"))

    implementation(libs.bundles.auth.service)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
