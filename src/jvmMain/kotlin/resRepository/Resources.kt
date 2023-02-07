package resRepository

import java.io.File

object Resources {

    fun getFile(patchInResource: String): File? =
        javaClass.classLoader.getResource(patchInResource)?.toURI()?.let(::File)

}