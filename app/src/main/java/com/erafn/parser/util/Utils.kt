package com.erafn.parser.util

import android.content.Context
import java.io.IOException

/**
 * Read the contents of a file in the /assets folder
 *
 * @param context : The target context
 * @param inFile : The name of the file in assets (e.g data.txt)
 *
 * @return A string representation of the file content
 */
fun readFile(context : Context, inFile: String): String {
    var content = ""

    try {
        val stream = context.assets.open(inFile)

        val size = stream.available()
        val buffer = ByteArray(size)
        stream.read(buffer)
        stream.close()
        content = String(buffer)
    } catch (e: IOException) {
        // TODO
    }
    return content
}