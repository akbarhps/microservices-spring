package com.charuniverse.microservices.authentication_service.model

data class LoginResponse(
    val username: String,
    val token: String,
)
