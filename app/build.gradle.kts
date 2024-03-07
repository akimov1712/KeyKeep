plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.navSafeArgs)
    alias(libs.plugins.hilt)
}

android {
    namespace = "ru.topbun.keyKeep"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.topbun.keyKeep"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {

//    Hilt
    implementation (libs.hiltAndroid)
    ksp (libs.hiltCompiler)

//    View
    implementation (libs.picasso)

//    Navigation
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)

//    JSON
    implementation (libs.gson)
    implementation (libs.jsoup)

//    Room
    implementation(libs.roomRuntime)
    ksp(libs.roomCompiler)
    implementation(libs.roomKtx)

//    ViewModel
    implementation (libs.viewModelLifecycle)

//    Default
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}