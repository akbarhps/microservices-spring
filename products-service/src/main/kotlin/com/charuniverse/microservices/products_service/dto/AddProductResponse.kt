package com.charuniverse.microservices.products_service.dto

import com.charuniverse.microservices.products_service.entity.User

data class AddProductResponse(
    val id: String,
    val name: String,
    val price: Float,
    val stock: Int,
    val user: User
)
