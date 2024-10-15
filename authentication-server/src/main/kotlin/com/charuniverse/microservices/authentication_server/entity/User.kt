package com.charuniverse.microservices.authentication_server.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private val id: String,

    private val username: String,

    private val email: String,
)
