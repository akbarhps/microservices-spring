package com.charuniverse.microservices.authentication_server.model

data class LoginRequest(
    val username: String,
    val password: String
)
