import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.kotlin.dsl.KotlinClosure1
import java.io.File
import java.io.FileInputStream
import java.util.*

operator fun Project.contains(propertyName: String): Boolean = hasProperty(propertyName)

fun Project.loadProperties(path: String, extra: ExtraPropertiesExtension) {
    val file: File = file(path)
    if (!file.exists()) return
    Properties().apply {
        load(FileInputStream(file))
        forEach { (k, v) ->
            extra["$k"] = v
        }
    }
}

/**
 * fix kotlin-dsl的写死返回值类型为Closure<Any?>, 导致类型不匹配的问题
 * @see [org.gradle.kotlin.dsl.closureOf]
 */
fun <T : Any> Any.unaryClosureOf(action: T.() -> T): Closure<T?> =
        KotlinClosure1(action, this, this)
