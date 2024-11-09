plugins {
    alias(libs.plugins.spring)
    java
}

dependencies {
    implementation(libs.bundles.data.service)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
