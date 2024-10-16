package com.charuniverse.microservices.authentication_service.repository

import com.charuniverse.microservices.authentication_service.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {

    fun findFirstByUsername(username: String): User?

}
