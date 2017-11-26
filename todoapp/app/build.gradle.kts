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
    compile(D.support.appcompat)
    compile(D.support.cardview)
    compile(D.support.design)
    compile(D.support.recyclerview)
    compile(D.support.v4)
    compile(D.espresso.idlingRes)
    compile(D.guava)
    compile(D.arch.room.runtime)

    kapt(D.arch.compiler)
    kapt(D.arch.room.compiler)

    // Dependencies for local unit tests
    testCompile(D.junit)
    testCompile(D.mockito.all)
    testCompile(D.hamcrest)

    // Android Testing Support Library"s runner and rules
    androidTestCompile(D.supportTest.runner)
    androidTestCompile(D.supportTest.rules)

    androidTestCompile(D.archTest.room)

    // Dependencies for Android unit tests
    androidTestCompile(D.junit)
    androidTestCompile(D.mockito.core)
    androidTestCompile(D.dexmaker.core)
    androidTestCompile(D.dexmaker.mockito)

    // Espresso UI Testing
    androidTestCompile(D.espresso.core)
    androidTestCompile(D.espresso.contrib)
    androidTestCompile(D.espresso.intents)
    androidTestCompile(D.espresso.idlingConcurrent)

    // Resolve conflicts between main and test APK:
    androidTestCompile(D.support.annotations)
    androidTestCompile(D.support.v4)
    androidTestCompile(D.support.recyclerview)
    androidTestCompile(D.support.appcompat)
    androidTestCompile(D.support.design)
}
