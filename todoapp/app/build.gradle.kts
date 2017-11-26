plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(D.compileSdkVersion)
    buildToolsVersion(D.buildToolsVersion)

    defaultConfig {
        minSdkVersion(D.minSdkVersion)
        targetSdkVersion(D.targetSdkVersion)

        applicationId = "com.example.android.architecture.blueprints.todomvpclean"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = false
            }
        }
    }

    compileOptions {
        setSourceCompatibility(D.javaVersion)
        setTargetCompatibility(D.javaVersion)
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true
            // Uses new built-in shrinker http://tools.android.com/tech-docs/new-build-system/built-in-shrinker
            isUseProguard = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            testProguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguardTest-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = true
            isUseProguard = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            testProguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguardTest-rules.pro")
        }
    }

    flavorDimensions("default")

    // If you need to add more flavors, consider using flavor dimensions.
    productFlavors {
        create("mock") {
            dimension = "default"
            applicationIdSuffix = ".mock"
        }
        create("prod") {
            dimension = "default"
        }
    }

    // Remove mockRelease as it"s not needed.
    variantFilter {
        setIgnore(buildType.name == "release" &&
            flavors.firstOrNull { it.name == "mock"} != null)
    }

    // Always show the result of every unit test, even if it passes.
    testOptions.unitTests.all(unaryClosureOf {
        testLogging {
            events("passed", "skipped", "failed", "standardOut", "standardError")
        }; this
    })
}

/*
 Dependency versions are defined in the top level build.gradle file. This helps keeping track of
 all versions in a single place. This improves readability and helps managing project complexity.
 */
dependencies {
    // App"s dependencies, including test
    implementation(D.support.appcompat)
    implementation(D.support.cardview)
    implementation(D.support.design)
    implementation(D.support.recyclerview)
    implementation(D.support.v4)
    implementation(D.espresso.idlingRes)
    implementation(D.guava)
    implementation(D.arch.room.runtime)

    kapt(D.arch.compiler)
    kapt(D.arch.room.compiler)

    // Dependencies for local unit tests
    testImplementation(D.junit)
    testImplementation(D.mockito.all)
    testImplementation(D.hamcrest)

    // Android Testing Support Library"s runner and rules
    androidTestImplementation(D.supportTest.runner)
    androidTestImplementation(D.supportTest.rules)

    androidTestImplementation(D.archTest.room)

    // Dependencies for Android unit tests
    androidTestImplementation(D.junit)
    androidTestImplementation(D.mockito.core)
    androidTestImplementation(D.dexmaker.core)
    androidTestImplementation(D.dexmaker.mockito)

    // Espresso UI Testing
    androidTestImplementation(D.espresso.core)
    androidTestImplementation(D.espresso.contrib)
    androidTestImplementation(D.espresso.intents)
    androidTestImplementation(D.espresso.idlingConcurrent)

    // Resolve conflicts between main and test APK:
    androidTestImplementation(D.support.annotations)
    androidTestImplementation(D.support.v4)
    androidTestImplementation(D.support.recyclerview)
    androidTestImplementation(D.support.appcompat)
    androidTestImplementation(D.support.design)
}
