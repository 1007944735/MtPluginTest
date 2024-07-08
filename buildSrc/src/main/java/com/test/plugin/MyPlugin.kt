package com.test.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.ide.eclipse.model.Output
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class MyPlugin: Plugin<Project>{
    override fun apply(target: Project) {
        try {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document = builder.newDocument()
        document.xmlStandalone = true

        val selectorEle = document.createElement("selector")
        selectorEle.setAttribute("xmlns:android", "http://schemas.android.com/apk/res/android")

        val itemEle = document.createElement("item")
        itemEle.setAttribute("android:drawable", "@drawable/ic_launcher_background.xml")
        itemEle.setAttribute("android:state_enabled", "true")

        selectorEle.appendChild(itemEle)

        document.appendChild(selectorEle)

        val tff = TransformerFactory.newInstance()
        val tf = tff.newTransformer()

        tf.setOutputProperty(OutputKeys.INDENT, "yes")

        val file = target.layout.buildDirectory.file("test.xml").get().asFile
            if (!file.exists()) {
                file.parentFile.mkdirs()
                file.createNewFile()
                println("not exists")
            }

            println("exists")


        tf.transform(DOMSource(document), StreamResult(file))

        }catch (e: Exception) {
            println(e.message)
        }

//        println(target.layout.buildDirectory.asFile.get().path)
    }
}