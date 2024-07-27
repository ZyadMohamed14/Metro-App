plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.metroapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.metroapp"
        minSdk = 21
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
    buildFeatures {
        viewBinding = true;
    }

}

dependencies {

    implementation ("com.google.code.gson:gson:2.8.8")
    implementation ("com.github.mumayank:AirLocation:2.5.2")
     //implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}