plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.model"
}

dependencies {

    implementation (Kotlin.core)
    implementation (Retrofit.converterGson)
}