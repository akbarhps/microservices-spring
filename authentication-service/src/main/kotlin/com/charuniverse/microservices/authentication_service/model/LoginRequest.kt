package com.charuniverse.microservices.authentication_service.model

data class LoginRequest(
    val username: String,
    val password: String
)
