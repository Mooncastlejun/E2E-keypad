package com.example.e2ekeypad.presentation.controller

import com.example.e2ekeypad.domain.service.KeypadService
import com.example.e2ekeypad.dto.KeypadResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class KeypadController(private val keypadService: KeypadService) {

    @GetMapping("/keypad")
    fun getKeypad(model: Model): String {
        val images = keypadService.generateRandomKeypad()
        val response = KeypadResponse(images)
        model.addAttribute("keypad", response)
        return "keypad"
    }
}
