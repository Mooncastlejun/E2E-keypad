package com.example.e2ekeypad.domain.service

import com.example.e2ekeypad.util.ImageUtil
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class KeypadService {

    fun generateRandomKeypad(): List<String> {
        val imageFiles = listOf(
            "static/keypad/keypad/_0.png",
            "static/keypad/keypad/_1.png",
            "static/keypad/keypad/_2.png",
            "static/keypad/keypad/_3.png",
            "static/keypad/keypad/_4.png",
            "static/keypad/keypad/_5.png",
            "static/keypad/keypad/_6.png",
            "static/keypad/keypad/_7.png",
            "static/keypad/keypad/_8.png",
            "static/keypad/keypad/_9.png",
            "static/keypad/keypad/_blank.png"
        )

        val base64Images = imageFiles.map { imageFile ->
            val resource = ClassPathResource(imageFile)
            resource.inputStream.use {
                ImageUtil.encodeImageToBase64(it)
            }
        }

        return base64Images.shuffled()
    }
}