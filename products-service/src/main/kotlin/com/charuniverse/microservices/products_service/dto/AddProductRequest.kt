package com.charuniverse.microservices.products_service.dto


data class AddProductRequest(
    val name: String,
    val price: Float,
    val stock: Int,
)
