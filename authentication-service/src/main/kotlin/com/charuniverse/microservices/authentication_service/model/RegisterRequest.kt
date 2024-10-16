package com.charuniverse.microservices.authentication_service.model

data class RegisterRequest(
    val username: String,
    val password: String
)
