package com.charuniverse.microservices.products_service.service

import com.charuniverse.microservices.products_service.entity.User
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class AuthenticationServiceFallback : AuthenticationService {

    private val LOGGER = LoggerFactory.getLogger(AuthenticationServiceFallback::class.java)

    override fun getUserInfo(token: String): ResponseEntity<User> {
        LOGGER.error("Authentication service is down")
        return ResponseEntity.internalServerError().build()
    }

}