plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.datasource"
}

dependencies {

    implementation(project(Modules.model))

    implementation(Kotlin.core)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.loggingInterceptor)
    implementation(Retrofit.converterGson)

    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.roomKtx)
}