package com.caetano.bruno.cartrawler.utils

import java.io.File

object JsonTestUtils {

    fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}