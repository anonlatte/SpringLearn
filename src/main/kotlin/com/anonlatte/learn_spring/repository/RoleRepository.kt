package com.anonlatte.learn_spring.repository

import com.anonlatte.learn_spring.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}