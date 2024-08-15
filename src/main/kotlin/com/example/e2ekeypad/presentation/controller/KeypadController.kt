package com.example.e2ekeypad.presentation.controller

import com.example.e2ekeypad.domain.service.KeypadService
import com.example.e2ekeypad.dto.KeypadResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/keypad")
class KeypadController(private val keypadService: KeypadService) {

    @GetMapping
    fun getKeypad(): KeypadResponse {
        return keypadService.generateKeypadImageBase64()
    }
}
