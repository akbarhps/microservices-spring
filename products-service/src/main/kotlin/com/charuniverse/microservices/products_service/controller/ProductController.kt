package com.charuniverse.microservices.products_service.controller

import com.charuniverse.microservices.products_service.dto.AddProductRequest
import com.charuniverse.microservices.products_service.dto.AddProductResponse
import com.charuniverse.microservices.products_service.entity.Product
import com.charuniverse.microservices.products_service.repository.ProductRepository
import com.charuniverse.microservices.products_service.service.AuthenticationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    @Autowired val authenticationService: AuthenticationService,
    @Autowired val productRepository: ProductRepository
) {
    private val LOGGER: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @GetMapping("/")
    fun getProducts(@RequestHeader token: String): ResponseEntity<List<Product>> {
        val response = authenticationService.getUserInfo(token)
        if (!response.hasBody()) {
            return ResponseEntity.notFound().build()
        }

        val user = response.body
        return if (user != null) {
            ResponseEntity.ok(productRepository.getProductsByUserId(user.id))
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/")
    fun addProducts(
        @RequestHeader token: String,
        @RequestBody request: AddProductRequest
    ): ResponseEntity<AddProductResponse> {
        val response = authenticationService.getUserInfo(token)
        if (!response.hasBody()) {
            return ResponseEntity.notFound().build()
        }

        LOGGER.info("User: ${response.body} added product: $request")
        val entity = Product(
            name = request.name,
            price = request.price,
            stock = request.stock,
            user = response.body!!
        )

        val product = productRepository.save(entity)
        return ResponseEntity.ok(
            AddProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                stock = product.stock,
                user = product.user
            )
        )
    }
}