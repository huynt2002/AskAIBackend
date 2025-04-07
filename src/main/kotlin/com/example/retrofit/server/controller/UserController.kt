package com.example.retrofit.server.controller

import com.example.retrofit.server.database.model.User
import com.example.retrofit.server.database.repository.UserRepository
import com.example.retrofit.server.util.getSecurityAuthUid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository) {
    @PostMapping
    fun save() {
        val uid = getSecurityAuthUid()
        userRepository.findByUid(uid)?.let {} ?: userRepository.save(User(uid))
    }
}
