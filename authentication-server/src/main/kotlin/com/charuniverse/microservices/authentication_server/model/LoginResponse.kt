package com.charuniverse.microservices.authentication_server.model

data class LoginResponse(
    val username: String,
    val token: String,
)
