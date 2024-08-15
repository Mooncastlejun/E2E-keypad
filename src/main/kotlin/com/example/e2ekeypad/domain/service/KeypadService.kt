package com.example.e2ekeypad.domain.service
import com.example.e2ekeypad.dto.KeypadResponse
import com.example.e2ekeypad.util.ImageUtil
import org.springframework.stereotype.Service

@Service
class KeypadService {

    fun generateKeypadImageBase64(): KeypadResponse {
        val imagePaths = (0..9).map { "src/main/resources/static/keypad/keypad/_$it.png" } +
                listOf("src/main/resources/static/keypad/keypad/_blank.png", "src/main/resources/static/keypad/keypad/_blank.png")
        val shuffledPaths = imagePaths.shuffled()

        val base64Image = ImageUtil.generateKeypadImage(shuffledPaths)
        val hashList = shuffledPaths.map { ImageUtil.getHashValue(it) }

        return KeypadResponse(base64Image, hashList)
    }
}