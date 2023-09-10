package com.anonlatte.learn_spring.repository

import com.anonlatte.learn_spring.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
}