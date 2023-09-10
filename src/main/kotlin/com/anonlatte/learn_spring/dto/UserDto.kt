package com.anonlatte.learn_spring.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class UserDto(
    val id: Long = 0,

    @NotEmpty
    val firstName: String = "",

    @NotEmpty
    val lastName: String = "",

    @NotEmpty(message = "Email should not be empty")
    @Email
    val email: String = "",

    @NotEmpty(message = "Password should not be empty")
    val password: String = ""
)