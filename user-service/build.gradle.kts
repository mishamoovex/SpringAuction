plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(project(":core"))
    implementation(project(":common:security"))

    implementation(platform(libs.spring.cloud.bom))
    implementation(libs.bundles.service.core)
    implementation(libs.bundles.service.data)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    testImplementation(libs.bundles.unit.testing)
}
