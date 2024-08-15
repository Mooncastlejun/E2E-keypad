package com.example.e2ekeypad.util

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.security.MessageDigest
import java.util.Base64
import java.util.Random
import javax.imageio.ImageIO

object ImageUtil {
    private const val IMAGE_WIDTH = 100
    private const val IMAGE_HEIGHT = 100
    private const val NUM_COLUMNS = 4
    private const val NUM_ROWS = 3

    private val random = Random()

    fun getHashValue(imagePath: String): String {
        if (imagePath.endsWith("_blank.png")) {
            return ""
        }

        val file = File(imagePath)
        val imageBytes = file.readBytes()
        val base64Encoded = Base64.getEncoder().encodeToString(imageBytes)
        val randomValue = random.nextInt(1000000)
        return hashString(base64Encoded + randomValue)
    }

    private fun hashString(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }

    fun generateKeypadImage(imagePaths: List<String>): String {
        val images = imagePaths.map { path ->
            ImageIO.read(File(path))
        }

        val mergedWidth = IMAGE_WIDTH * NUM_COLUMNS
        val mergedHeight = IMAGE_HEIGHT * NUM_ROWS
        val mergedImage = BufferedImage(mergedWidth, mergedHeight, BufferedImage.TYPE_INT_ARGB)
        val g2d = mergedImage.createGraphics()
        g2d.paint = java.awt.Color.WHITE
        g2d.fillRect(0, 0, mergedWidth, mergedHeight)

        images.forEachIndexed { index, image ->
            val row = index / NUM_COLUMNS
            val col = index % NUM_COLUMNS

            // 이미지가 위치할 영역의 좌표 계산
            val x = col * IMAGE_WIDTH
            val y = row * IMAGE_HEIGHT

            // 이미지 중앙 배치
            val imgX = x + (IMAGE_WIDTH - image.width) / 2
            val imgY = y + (IMAGE_HEIGHT - image.height) / 2

            g2d.drawImage(image, imgX, imgY, null)
        }

        g2d.dispose()

        val outputStream = ByteArrayOutputStream()
        ImageIO.write(mergedImage, "PNG", outputStream)
        val imageBytes = outputStream.toByteArray()
        return Base64.getEncoder().encodeToString(imageBytes)
    }
}
