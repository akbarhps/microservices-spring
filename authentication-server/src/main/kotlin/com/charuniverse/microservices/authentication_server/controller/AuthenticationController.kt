package com.charuniverse.microservices.authentication_server.controller

import com.charuniverse.microservices.authentication_server.entity.User
import com.charuniverse.microservices.authentication_server.model.LoginRequest
import com.charuniverse.microservices.authentication_server.model.LoginResponse
import com.charuniverse.microservices.authentication_server.model.RegisterRequest
import com.charuniverse.microservices.authentication_server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class AuthenticationController(@Autowired val userRepository: UserRepository) {

    @PostMapping("/api/register")
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<LoginResponse> {
        val entity = User(username = registerRequest.username, password =  registerRequest.password)
        val user = userRepository.save(entity)
        return ResponseEntity.ok(LoginResponse(user.username, "token-${user.id}"))
    }

    @PostMapping("/api/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val user = userRepository.findFirstByUsername(loginRequest.username) ?: return ResponseEntity.badRequest().build()
        if (user.password != loginRequest.password) {
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity.ok(LoginResponse(user.username, "token-${user.id}"))
    }
}