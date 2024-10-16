package com.charuniverse.microservices.products_service.repository

import com.charuniverse.microservices.products_service.entity.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, String> {

    fun getProductsByUserId(userId: String): List<Product>

}