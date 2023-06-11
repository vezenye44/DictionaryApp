plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.repository"
}

dependencies {

    implementation(project(Modules.model))
    implementation(project(Modules.dataSource))

    implementation(Kotlin.core)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.loggingInterceptor)
    implementation(Retrofit.converterGson)
}