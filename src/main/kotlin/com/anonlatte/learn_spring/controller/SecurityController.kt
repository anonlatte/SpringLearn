package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.dto.UserDto
import com.anonlatte.learn_spring.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class SecurityController(private val userService: UserService) {

    @GetMapping("/index")
    fun index(): String {
        return "index"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

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
        model.addAttribute("users", users)
        return "users"
    }
}