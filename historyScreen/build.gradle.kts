plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.historyscreen"
}

dependencies {

    implementation(project(Modules.model))
    implementation(project(Modules.core))
    implementation(project(Modules.repository))

    implementation(Kotlin.core)
    implementation(Kotlin.coroutinesCore)

    implementation(Android.appcompat)
    implementation(Android.material)

    implementation(Koin.koinAndroid)
}