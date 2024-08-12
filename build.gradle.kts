plugins {
    id("com.diffplug.spotless") version "7.0.0.BETA2" apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        val androidGradlePluginVersion: String by project
        val kotlinVersion: String by project
        classpath("com.android.tools.build:gradle:$androidGradlePluginVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    apply {
        plugin("com.diffplug.spotless")
    }
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        java {
            target("**/*.java")
            googleJavaFormat().aosp()
            trimTrailingWhitespace()
        }
        kotlin {
            target("**/*.kt")
            ktlint()
            trimTrailingWhitespace()
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
            trimTrailingWhitespace()
        }
    }
}
