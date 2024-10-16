package com.charuniverse.microservices.authentication_server.controller

import com.charuniverse.microservices.authentication_server.entity.User
import com.charuniverse.microservices.authentication_server.model.LoginRequest
import com.charuniverse.microservices.authentication_server.model.LoginResponse
import com.charuniverse.microservices.authentication_server.model.RegisterRequest
import com.charuniverse.microservices.authentication_server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(@Autowired val userRepository: UserRepository) {

    @GetMapping("/{token}")
    fun getUserInfo(@PathVariable(name = "token") token: String): ResponseEntity<User> {
        val user = userRepository.findById(token.substringAfter("token-"))
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<LoginResponse> {
        val entity = User(username = registerRequest.username, password = registerRequest.password)
        val user = userRepository.save(entity)
        return ResponseEntity.ok(LoginResponse(user.username, "token-${user.id}"))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val user = userRepository.findFirstByUsername(loginRequest.username)
            ?: return ResponseEntity.badRequest().build()

        if (user.password != loginRequest.password) {
            return ResponseEntity.badRequest().build()
        }

        return ResponseEntity.ok(LoginResponse(user.username, "token-${user.id}"))
    }
}