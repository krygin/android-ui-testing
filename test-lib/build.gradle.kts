val kotlinVersion: String by project
val playServicesVersion: String by project
val targetSdk: String by project
val minSdk: String by project
val supportVersion: String by project

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(targetSdk.toInt())

    defaultConfig {
        minSdkVersion(minSdk)
        targetSdkVersion(targetSdk.toInt())
        testInstrumentationRunner = "com.avito.android.ui.test.UITestRunner"
    }

    buildTypes {
        getByName("debug") {
            matchingFallbacks = listOf("release")
        }
    }

    variantFilter {
        if (name == "release") {
            setIgnore(true)
        }
    }

    packagingOptions {
        pickFirst("protobuf.meta")
    }
}

dependencies {
    implementation(kotlin("stdlib", kotlinVersion))
    implementation("com.google.android.gms:play-services-maps:$playServicesVersion")
    implementation("com.android.support:appcompat-v7:$supportVersion")
    implementation("com.android.support:recyclerview-v7:$supportVersion")
    implementation("com.android.support:design:$supportVersion")

    androidTestImplementation(project(":ui-testing-core"))
}

tasks.getByName("build").dependsOn("$path:assembleAndroidTest")