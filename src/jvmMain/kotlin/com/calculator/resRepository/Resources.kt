package com.calculator.resRepository

import java.io.InputStream

object Resources {

    private fun getInputStream(patchInResource: String): InputStream? =
        javaClass.classLoader.getResourceAsStream(patchInResource)

    fun <T> getAndUseInputStream(patchInResource: String, block: (InputStream?) -> T) =
        getInputStream(patchInResource).use(block)

}