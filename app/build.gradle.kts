plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":feature:chat"))
    implementation(project(":feature:login"))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}