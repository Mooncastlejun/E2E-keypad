package com.example.e2ekeypad.util

import java.util.Base64
import java.io.InputStream

object ImageUtil {
    fun encodeImageToBase64(inputStream: InputStream): String {
        return Base64.getEncoder().encodeToString(inputStream.readBytes())
    }
}