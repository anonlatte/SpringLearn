package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.dto.UserDto
import com.anonlatte.learn_spring.entity.User

interface UserService {
    fun save(userDto: UserDto)

    fun findByEmail(email: String): User?

    fun findAll(): List<UserDto>
}