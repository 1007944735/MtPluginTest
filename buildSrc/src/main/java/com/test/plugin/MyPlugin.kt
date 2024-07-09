package com.test.plugin

import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.SourceDirectories
import com.android.build.api.variant.Sources
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction
import org.gradle.plugins.ide.eclipse.model.Output
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class MyPlugin : Plugin<Project> {


    override fun apply(target: Project) {
        target.extensions.create("Config", Config::class.java)
        val androidComponents = target.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
//            try {
//                val factory = DocumentBuilderFactory.newInstance()
//                val builder = factory.newDocumentBuilder()
//                val document = builder.newDocument()
//                document.xmlStandalone = true
//
//                val selectorEle = document.createElement("selector")
//                selectorEle.setAttribute(
//                    "xmlns:android",
//                    "http://schemas.android.com/apk/res/android"
//                )
//
//                val itemEle = document.createElement("item")
//                itemEle.setAttribute("android:drawable", "@drawable/ic_launcher_background.xml")
//                itemEle.setAttribute("android:state_enabled", "true")
//
//                selectorEle.appendChild(itemEle)
//
//                document.appendChild(selectorEle)
//
//                val tff = TransformerFactory.newInstance()
//                val tf = tff.newTransformer()
//
//                tf.setOutputProperty(OutputKeys.INDENT, "yes")
//
//                val file =
//                    target.layout.buildDirectory.file("${variant.flavorName}/test.xml").get().asFile
//                if (!file.exists()) {
//                    file.parentFile.mkdirs()
//                    file.createNewFile()
//                    println("not exists")
//                }
//
//                println("exists")
//
//
//                tf.transform(DOMSource(document), StreamResult(file))
//
//            } catch (e: Exception) {
//                println(e.message)
//            }


            val assetCreationTask =
                target.tasks.register<AssetCreatorTask>("create${variant.name}Drawable", AssetCreatorTask::class.java)

            variant.sources.res?.addGeneratedSourceDirectory(assetCreationTask, AssetCreatorTask::outputDirectory)
        }

//        println(target.layout.buildDirectory.asFile.get().path)
    }

    public open class Config {
        var name: String = ""
    }

    abstract class AssetCreatorTask : DefaultTask() {
        @get:OutputDirectory
        abstract val outputDirectory: DirectoryProperty

        @ExperimentalStdlibApi
        @TaskAction
        fun taskAction() {
            val file = File(outputDirectory.get().asFile, "drawable")
            file.mkdirs()

            File(file, "custom_asset.xml")
                .writeText("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<selector xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                        "\n" +
                        "</selector>")
        }
    }

}