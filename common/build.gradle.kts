plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "io.github.guowenlong.chatgptforandroid.common"

    compileSdk = Configurations.compileSdk

    defaultConfig {
        minSdk = Configurations.minSdk
        targetSdk = Configurations.targetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(libs.androidx.activity.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.cardview)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.core.ktx)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.recyclerview)
    api(libs.androidx.room.room.ktx)
    api(libs.androidx.room.compiler)
    api(libs.androidx.room.runtime)
    api(libs.glide)
    api(libs.glide.compiler)
    api(libs.glide.okhttp3)
    api(libs.google.material)
    api(libs.koin.android)
    api(libs.kotlinx.coroutines.android)
    api(libs.okhttp.log)
    api(libs.okhttp.log)
    api(libs.retrofit)
    api(libs.retrofit.converter.moshi)
    api(libs.toaster)
    api(libs.multitype)
    api(libs.xxpermission)

    api(project(":model"))
}