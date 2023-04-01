plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "io.github.guowenlong.chatgptforandroid.login"

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
    api(project(":common"))
    api(project(":repository"))
}