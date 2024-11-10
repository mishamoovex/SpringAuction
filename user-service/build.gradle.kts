plugins {
    alias(libs.plugins.spring)
    java
}

dependencies {
    implementation(project(":core"))

    implementation(libs.bundles.data.service)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    testImplementation(libs.bundles.unit.testing)
}
