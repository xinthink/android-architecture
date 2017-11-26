import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

/**
 * Dependencies definition
 */
object D {
    // Android SDK versions
    val androidPluginVersion = "3.0.1"
    val minSdkVersion = 14
    val targetSdkVersion = 27
    val compileSdkVersion = 27
    val buildToolsVersion = "27.0.1"

    // Java/Kotlin/Anko
    val javaVersion = JavaVersion.VERSION_1_7
    val kotlinVersion = "1.1.61"
    val kotlinStdlib = "stdlib-jre7"

    // Lib versions
    val supportLibVersion = "27.0.1"
    val archVersion= "1.0.0"
    val guavaVersion = "18.0"
    val jsr305Version = "3.0.1"

    // Testing lib versions
    val junitVersion = "4.12"
    val mockitoVersion = "1.10.19"
    val powerMockito = "1.6.2"
    val hamcrestVersion = "1.3"
    val runnerVersion = "1.0.1"
    val rulesVersion = "1.0.1"
    val espressoVersion = "3.0.1"

    // Dependencies
    val androidPlugin = "com.android.tools.build:gradle:$androidPluginVersion"

    object Support {
        val appcompat = "com.android.support:appcompat-v7:$supportLibVersion"
        val v4 = "com.android.support:support-v4:$supportLibVersion"
        val annotations = "com.android.support:support-annotations:$supportLibVersion"
        val design = "com.android.support:design:$supportLibVersion"
        val cardview = "com.android.support:cardview-v7:$supportLibVersion"
        val recyclerview = "com.android.support:recyclerview-v7:$supportLibVersion"
    }
    val support = Support

    object Arch {
        val runtime = "android.arch.lifecycle:runtime:$archVersion"
        val extensions = "android.arch.lifecycle:extensions:$archVersion"
        val compiler = "android.arch.lifecycle:compiler:$archVersion"

        object Room {
            val runtime = "android.arch.persistence.room:runtime:$archVersion"
            val compiler = "android.arch.persistence.room:compiler:$archVersion"
            val rx = "android.arch.persistence.room:rxjava2:$archVersion"
        }
        val room = Room
    }
    val arch = Arch

    val guava = "com.google.guava:guava:$guavaVersion"

    // Testing dependencies
    val junit = "junit:junit:$junitVersion"
    val hamcrest = "org.hamcrest:hamcrest-all:$hamcrestVersion"

    object Mockito {
        val all = "org.mockito:mockito-all:$mockitoVersion"
        val core = "org.mockito:mockito-core:$mockitoVersion"
    }
    val mockito = Mockito

    object SupportTest {
        val runner = "com.android.support.test:runner:$runnerVersion"
        val rules = "com.android.support.test:rules:$rulesVersion"
    }
    val supportTest = SupportTest

    object ArchTest {
        val room = "android.arch.persistence.room:testing:$archVersion"
    }
    val archTest = ArchTest

    object Espresso {
        val core = "com.android.support.test.espresso:espresso-core:$espressoVersion"
        val contrib = "com.android.support.test.espresso:espresso-contrib:$espressoVersion"
        val intents = "com.android.support.test.espresso:espresso-intents:$espressoVersion"
        val idlingConcurrent = "com.android.support.test.espresso.idling:idling-concurrent:$espressoVersion"
        val idlingRes = "com.android.support.test.espresso:espresso-idling-resource:$espressoVersion"
    }
    val espresso = Espresso

    object Dexmaker {
        val core = "com.google.dexmaker:dexmaker:1.2"
        val mockito = "com.google.dexmaker:dexmaker-mockito:1.2"
    }
    val dexmaker = Dexmaker
}

/** Kotlin dependency with default stdlib module */
val DependencyHandler.kt get() = kotlin(D.kotlinStdlib)
