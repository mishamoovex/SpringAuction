plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    java
}

dependencies {
    implementation(libs.hibernate)
    implementation(libs.spring.web)
    implementation(libs.spring.validation)
    implementation(libs.spring.security)
    implementation(libs.webtoken)
    implementation(libs.model.mapper)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
