import ru.topbun.buildsrc.MetaData

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.navSafeArgs)
    alias(libs.plugins.hilt)
}

android {
    namespace = MetaData.applicationId
    compileSdk = MetaData.compileSdkVersion

    defaultConfig {
        applicationId = MetaData.applicationId
        minSdk = MetaData.minSdkVersion
        targetSdk = MetaData.targetSdkVersion
        versionCode = MetaData.versionCode
        versionName = MetaData.versionName

        testInstrumentationRunner = MetaData.testInstrumentationRunner
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures{
        viewBinding = true
    }
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {

//    Hilt
    implementation (libs.hiltAndroid)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    ksp (libs.hiltCompiler)

//    View
    implementation (libs.picasso)
    implementation (libs.otpview)

//    Navigation
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)

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