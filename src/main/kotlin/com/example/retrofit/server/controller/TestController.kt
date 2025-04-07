package com.example.retrofit.server.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
    @GetMapping
    fun test(): String {
        return "hello " + SecurityContextHolder.getContext().authentication.principal as String
    }
}
