import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.0.0-alpha3"
    id("com.android.library")
    id("maven-publish")
}

group = "androidx.constraintlayout.compose"
version = "1.0"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(project(mapOf("path" to ":core")))
            }
        }
        val commonTest by getting {
            dependencies {
//                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.2.0")
                api("androidx.core:core-ktx:1.3.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                api("org.json:json:20210307")
            }
        }
        val desktopTest by getting
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(module("androidx.constraintlayout:constraintlayout-core"))
    }
}
configurations.configureEach {
    resolutionStrategy.eachDependency {
        if (
            requested.group == "androidx.lifecycle" &&
            requested.name != "lifecycle-viewmodel-compose"
        ){
            useVersion("2.3.0-rc01")
        }
        if (requested.group == "androidx.savedstate") {
            useVersion("1.1.0-rc01")
        }
    }
}



afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.huixing.constraintlayout"
                artifactId = "library"
                version = "1.0"
            }
        }
    }
}

