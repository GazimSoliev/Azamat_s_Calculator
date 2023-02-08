package com.calculator.resRepository

import java.io.InputStream

object Resources {

    fun getInputStream(patchInResource: String): InputStream? =
        javaClass.classLoader.getResourceAsStream(patchInResource)

}