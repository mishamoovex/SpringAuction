plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(libs.hibernate)
    implementation(libs.spring.web)
    implementation(libs.spring.validation)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
