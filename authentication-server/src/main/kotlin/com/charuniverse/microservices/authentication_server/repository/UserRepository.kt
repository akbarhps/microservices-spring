package com.charuniverse.microservices.authentication_server.repository

import com.charuniverse.microservices.authentication_server.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findFirstByUsername(username: String): User?

}
