package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping("/illegal")
    fun throwIllegalArgumentException() {
        throw IllegalArgumentException("This is an illegal argument exception!")
    }

    @GetMapping("/exception")
    fun throwException() {
        throw Exception("This is a general exception!")
    }
}
