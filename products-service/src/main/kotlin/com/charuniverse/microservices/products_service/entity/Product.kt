package com.charuniverse.microservices.products_service.entity

import jakarta.persistence.*

@Entity(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    val name: String,

    val price: Float,

    val stock: Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)
