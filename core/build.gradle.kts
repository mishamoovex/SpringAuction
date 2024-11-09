plugins {
    java
}

dependencies {
    implementation(libs.hibernate)
    implementation(libs.spring.web)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
