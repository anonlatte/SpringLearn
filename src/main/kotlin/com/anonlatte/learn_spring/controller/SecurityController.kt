package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.db.entity.Role
import com.anonlatte.learn_spring.db.entity.RoleNames
import com.anonlatte.learn_spring.db.entity.User.Companion.toDto
import com.anonlatte.learn_spring.domain.repository.RoleRepository
import com.anonlatte.learn_spring.domain.service.UserService
import com.anonlatte.learn_spring.dto.UserDto
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
class SecurityController(
    private val userService: UserService,
    private val roleRepository: RoleRepository
) {

    private val logger = LoggerFactory.getLogger(SecurityController::class.java)

    @GetMapping("/index")
    fun index(): String = "index"

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/register")
    fun showRegistrationForm(model: Model): String {
        model.addAttribute("user", UserDto())
        return "register"
    }

    @PostMapping("/register/save")
    fun registration(
        @ModelAttribute("user") userDto: UserDto,
        result: BindingResult,
        model: Model
    ): String {
        val existingUser = userService.findByEmail(userDto.email)

        if (existingUser != null) {
            result.rejectValue(
                "email",
                "409",
                "There is already an account registered with that email"
            )
        }

        return if (result.hasErrors()) {
            model.addAttribute("user", userDto)
            "register"
        } else {
            userService.save(userDto)
            "redirect:/register?success"
        }
    }

    @GetMapping("/users")
    fun getUsers(model: Model): String {
        val users = userService.findAll()
        val currentUserEmail = SecurityContextHolder.getContext().authentication?.name.orEmpty()
        model.addAttribute("users", users)
        model.addAttribute("currentUserEmail", currentUserEmail)
        return "users"
    }

    @RequestMapping("/users/delete")
    fun deleteUser(@RequestParam("userId") id: Long): String {
        userService.deleteById(id)
        return "redirect:/users"
    }

    @RequestMapping("/users/updateRole")
    fun changeRole(@RequestParam("userId") userId: Long): ModelAndView {
        val isUserAdmin = userService.getById(userId)?.roles?.any { it.name == RoleNames.ROLE_ADMIN } == true
        return ModelAndView("change-role")
            .addObject("roleChange", RoleChangeDto(isUserAdmin))
            .addObject("userId", userId)
    }

    @Transactional
    @RequestMapping("/users/updateRole", method = [RequestMethod.POST])
    fun updateRole(
        @RequestParam userId: Long,
        @ModelAttribute roleChange: RoleChangeDto
    ): String {

        userService.getById(userId)?.run {
            val roles: Set<Role> = roleRepository.findByName(RoleNames.ROLE_ADMIN)?.let {
                if (roleChange.isAdmin == null) {
                    roles.plus(it)
                } else {
                    roles.minus(it)
                }
            }.orEmpty()
            userService.updateRoles(this.toDto(), roles)
            logger.info("User $email has been updated to ${if (roleChange.isAdmin == null) "admin" else "user"}")
        }
        return "redirect:/users"
    }
}

