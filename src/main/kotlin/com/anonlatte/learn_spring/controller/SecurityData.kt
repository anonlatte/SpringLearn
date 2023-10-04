package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.db.entity.RoleNames
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

object SecurityData {
    val currentAuth: Authentication?
        get() = SecurityContextHolder.getContext().authentication
    val isAdmin: Boolean
        get() = currentAuth?.authorities.orEmpty()
            .any { it.authority == RoleNames.ROLE_ADMIN }
}