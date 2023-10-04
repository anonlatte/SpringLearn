package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.db.entity.Role
import com.anonlatte.learn_spring.db.entity.RoleNames
import com.anonlatte.learn_spring.db.entity.User
import com.anonlatte.learn_spring.db.entity.User.Companion.toDto
import com.anonlatte.learn_spring.domain.repository.RoleRepository
import com.anonlatte.learn_spring.domain.repository.UserRepository
import com.anonlatte.learn_spring.domain.service.UserService
import com.anonlatte.learn_spring.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    @Transactional
    override fun save(userDto: UserDto) {
        val role: Role = roleRepository.findByName(RoleNames.ROLE_USER) ?: createRole(RoleNames.ROLE_USER)
        val user = User(
            id = userDto.id,
            name = "${userDto.firstName} ${userDto.lastName}",
            email = userDto.email,
            password = passwordEncoder.encode(userDto.password),
            roles = setOf(role),
            employees = emptySet()
        )
        userRepository.save(user)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun getById(id: Long): User? = userRepository.findById(id).orElse(null)

    override fun findAll(): List<UserDto> {
        return userRepository.findAll().map { it.toDto() }
    }

    override fun deleteById(id: Long) {
        userRepository.deleteById(id)
    }

    override fun updateRoles(userDto: UserDto, roles: Set<Role>) {
        val user = User(
            id = userDto.id,
            name = "${userDto.firstName} ${userDto.lastName}",
            email = userDto.email,
            password = passwordEncoder.encode(userDto.password),
            roles = roles,
            employees = emptySet()
        )
        userRepository.save(user)
    }

    private fun createRole(roleName: String): Role {
        val role = Role(name = roleName)
        return roleRepository.save(role)
    }
}