package com.anonlatte.learn_spring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MyController {

    @GetMapping("/hello")
    fun showMyFirstPage(
        @RequestParam(name = "name", required = false, defaultValue = "World")
        name: String,
        model: Model
    ): String {
        model.addAttribute("name", name)
        return "my-first-page"
    }
}