package com.anonlatte.learn_spring.domain.service

import com.anonlatte.learn_spring.db.entity.User
import com.anonlatte.learn_spring.dto.UserDto

interface UserService {
    fun save(userDto: UserDto)

    fun findByEmail(email: String): User?

    fun findAll(): List<UserDto>
}