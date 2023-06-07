plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.networkstatus"
}

dependencies {

    implementation(Kotlin.core)
    implementation(Android.appcompat)
}