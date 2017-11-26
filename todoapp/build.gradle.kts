plugins {
    kotlin("android") version D.kotlinVersion apply false
    kotlin("kapt") version D.kotlinVersion apply false
}

buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(D.androidPlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
