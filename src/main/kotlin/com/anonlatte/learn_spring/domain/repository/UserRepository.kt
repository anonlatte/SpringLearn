package com.anonlatte.learn_spring.domain.repository

import com.anonlatte.learn_spring.db.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
}