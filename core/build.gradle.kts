plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.core"
}

dependencies {

    implementation(project(Modules.model))

    implementation(Kotlin.core)
    implementation(Android.appcompat)
    implementation(Android.material)

}