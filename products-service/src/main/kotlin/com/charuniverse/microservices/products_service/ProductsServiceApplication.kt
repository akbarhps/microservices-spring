package com.charuniverse.microservices.products_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ProductsServiceApplication

fun main(args: Array<String>) {
	runApplication<ProductsServiceApplication>(*args)
}
