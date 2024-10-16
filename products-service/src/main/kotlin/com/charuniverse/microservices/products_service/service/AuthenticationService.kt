package com.charuniverse.microservices.products_service.service

import com.charuniverse.microservices.products_service.entity.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "authentication-service", fallback = AuthenticationServiceFallback::class)
interface AuthenticationService {

    @GetMapping("/api/auth/{token}")
    fun getUserInfo(@PathVariable token: String): ResponseEntity<User>

}