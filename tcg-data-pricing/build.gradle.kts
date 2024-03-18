import com.codingfeline.buildkonfig.compiler.FieldSpec

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(dolbyio.plugins.android.library)
    alias(dolbyio.plugins.kotlin.multiplatform)
    alias(dolbyio.plugins.kotlin.serialization)
    alias(dolbyio.plugins.multiplatform.buildkonfig)
    id("iosSimulatorConfiguration")
    id("jvmCompat")
    id("publication")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        publishLibraryVariants("release")
    }

    jvm()

    js(IR) {
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(dolbyio.kotlinx.coroutines)
                api(dolbyio.kotlinx.serialization.json)
                api(dolbyio.multiplatform.http.client)

                api(libs.tcg.mapper)
            }
        }
        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
                api(dolbyio.kotlinx.coroutines.test)
                api(dolbyio.multiplatform.platform)
            }
        }

        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val jvmMain by getting
        val jsMain by getting

        listOf(
            androidMain,
            iosX64Main,
            iosArm64Main,
            iosSimulatorArm64Main,
            jvmMain,
            jsMain
        ).forEach { it.dependsOn(commonMain) }
    }
}

android {
    namespace = "eu.codlab.tcg.pricing"
}

buildkonfig {
    packageName = "eu.codlab.tcg.pricing.buildconfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "commit", rootProject.extra["commit"] as String)
    }
}
