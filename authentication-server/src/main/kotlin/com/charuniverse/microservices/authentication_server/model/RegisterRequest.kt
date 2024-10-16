package com.charuniverse.microservices.authentication_server.model

data class RegisterRequest(
    val username: String,
    val password: String
)
