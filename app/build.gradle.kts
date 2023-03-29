plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "io.github.guowenlong.chatgptforandroid"

    compileSdk = Configurations.compileSdk

    defaultConfig {
        applicationId = "io.github.guowenlong.chatgptforandroid"

        minSdk = Configurations.minSdk
        targetSdk = Configurations.targetSdk
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(project(":feature:chat"))
    api(project(":feature:login"))
}