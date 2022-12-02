plugins {
    kotlin("multiplatform") version "1.7.20"
    id("org.jetbrains.compose") version "1.2.1"
}

group = "com.duchastel.simon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
            }
        }
    }
}

dependencies {
    //React, React DOM + Wrappers
//    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.354"))
//    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
//    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
//
//    //Kotlin React Emotion (CSS)
//    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
//
//    //Coroutines & serialization
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}

//rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
//    versions.webpackCli.version = "4.10.0"
//}