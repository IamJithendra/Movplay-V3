import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties


fun getApiKey(): String {
    return gradleLocalProperties(rootDir).getProperty("TMDB_API_KEY")
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    kotlin("kapt")
    // TOO move to alias
    id("kotlin-parcelize")
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.perf)
    alias(libs.plugins.google.gms.services)
}

android {
    namespace = "com.jvktech.moviebuff"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jvktech.moviebuff"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "TMDB_API_KEY", getApiKey())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {

    // Compose
    implementation(libs.core.ktx)
    implementation(libs.compose.ui)
//    implementation("androidx.compose.ui:ui:1.3.0-rc01")
    implementation(libs.compose.material3)
    implementation(libs.compose.material)
    implementation(libs.compose.constraintlayout)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.compose.lottie)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.compose.activity)
    implementation(libs.test.runner)

    // Splash Screen API
    implementation(libs.core.splashscreen)

    // Foundation
    implementation(libs.compose.foundation)

    // Paging-Compose
    implementation(libs.paging.compose)

    // Icon
    implementation(libs.compose.material.icons.extended)

    // Dagger-hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.compose.navigation)
    kapt(libs.hilt.compiler)

    // Moshi
    implementation(libs.moshi)
    ksp(libs.moshi.kotlin.codegen)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.moshi.converter)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx)

    // Coil
    implementation(libs.coil.compose)

    // Room
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    // Timber
    implementation(libs.timber)

    // Compose-Destinations
    implementation(libs.raamcosta.compose.destinations.animations.core)
    ksp(libs.raamcosta.compose.destinations.ksp)

    // Accompanist
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.placeholder)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.permissions)

    // Chucker
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)

    //Palette
    implementation(libs.palette.ktx)

    // Unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

}