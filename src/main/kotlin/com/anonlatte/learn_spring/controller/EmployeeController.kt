package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.db.entity.Employee
import com.anonlatte.learn_spring.domain.repository.EmployeeRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class EmployeeController(
    @Autowired private val employeRepository: EmployeeRepository
) {

    private val logger = LoggerFactory.getLogger(EmployeeController::class.java)
    private val isAdmin = SecurityContextHolder.getContext().authentication?.authorities.orEmpty()
        .any { it.authority == "ROLE_ADMIN" }

    @GetMapping("/employees")
    fun getEmployees(): ModelAndView {
        logger.info("/list -> connection")
        val modelAndView = ModelAndView("list-employees")
        modelAndView.addObject("employees", employeRepository.findAll())
        modelAndView.addObject("isAdmin", isAdmin)
        return modelAndView
    }

    @GetMapping("/employees/add")
    fun getEmployee(): ModelAndView {
        val modelAndView = ModelAndView("add-employee-form")
        val employee = Employee()
        modelAndView.addObject("employee", employee)
        modelAndView.addObject("isAdmin", isAdmin)
        return modelAndView
    }

    @PostMapping("/employees/save")
    fun saveEmployee(@ModelAttribute employee: Employee): String {
        employeRepository.save(employee)
        return "redirect:/employees"
    }

    @GetMapping("/employees/update")
    fun getEmployee(@RequestParam employeeId: Long): ModelAndView {
        val modelAndView = ModelAndView("add-employee-form")
        val optionalEmployee = employeRepository.findById(employeeId)
        val employee = if (optionalEmployee.isPresent) {
            optionalEmployee.get()
        } else {
            Employee()
        }
        modelAndView.addObject("employee", employee)
        modelAndView.addObject("isAdmin", isAdmin)
        return modelAndView
    }

    @GetMapping("/employees/delete")
    fun deleteEmployee(@RequestParam employeId: Long): String {
        employeRepository.deleteById(employeId)
        return "redirect:/employees"
    }
}

