plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}
android {
    namespace = "io.github.guowenlong.chatgpt"

    compileSdk = Configurations.compileSdk

    defaultConfig {
        minSdk = Configurations.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding {
        enable = true
    }
}

dependencies {
    api(libs.retrofit)
    api(libs.okhttp.log)
    api(libs.moshi)
    api(libs.retrofit.converter.moshi)
    kapt(libs.moshi.codegen)
}