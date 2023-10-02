package com.anonlatte.learn_spring.domain.repository

import com.anonlatte.learn_spring.db.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}