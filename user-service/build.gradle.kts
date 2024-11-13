plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(project(":core"))
    implementation(platform(libs.spring.cloud.bom))

    implementation(libs.bundles.data.service.core)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    testImplementation(libs.bundles.unit.testing)
}
