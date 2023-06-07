import org.gradle.api.JavaVersion
object Config {
    const val applicationId = "com.example.dictionaryapp"
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    val javaVersion = JavaVersion.VERSION_1_8
}
object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Modules {

    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val dataSource = ":dataSource"
    const val networkStatus = ":networkStatus"

    const val historyScreen = ":historyScreen"
}

object Versions {

    //Android
    const val appcompat = "1.6.1"
    const val material = "1.9.0"
    const val swipeRefreshLayout = "1.1.0"

    //Kotlin
    const val core = "1.10.1"

    //Coroutines
    const val coroutines = "1.7.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val interceptor = "4.11.0"

    //Koin
    const val koin = "3.4.0"

    //Glide
    const val glide = "4.15.1"

    //Picasso
    const val picasso = "2.8"

    //Coil
    const val coil = "1.4.0"

    //Room
    const val room = "2.5.1"

    //Test
    const val jUnit = "4.13.2"
    const val androidxJunit = "1.1.5"
    const val espressoCore = "3.5.1"
}

object Android {

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object Kotlin {

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Retrofit {

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {

    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
}

object ImageLoad {

    const val coil = "io.coil-kt:coil:${Versions.coil}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Room {

    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object TestImpl {

    const val junit = "junit:junit:${Versions.jUnit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}