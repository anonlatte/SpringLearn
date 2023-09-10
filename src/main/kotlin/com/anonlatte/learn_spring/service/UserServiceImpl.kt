package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.dto.UserDto
import com.anonlatte.learn_spring.entity.Role
import com.anonlatte.learn_spring.entity.User
import com.anonlatte.learn_spring.repository.RoleRepository
import com.anonlatte.learn_spring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun save(userDto: UserDto) {
        val role = roleRepository.findByName("ROLE_ADMIN") ?: checkRoleExists()
        val user = User(
            name = "${userDto.firstName} ${userDto.lastName}",
            email = userDto.email,
            password = passwordEncoder.encode(userDto.password),
            roles = listOfNotNull(role)
        )
        userRepository.save(user)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun findAll(): List<UserDto> {
        return userRepository.findAll().map { it.toDto() }
    }

    private fun User.toDto() = UserDto(
        id = id,
        firstName = name.split(" ")[0],
        lastName = name.split(" ")[1],
        email = email,
        password = password
    )

    private fun checkRoleExists(): Role {
        val role = Role(name = "ROLE_ADMIN")
        return roleRepository.save(role)
    }
}