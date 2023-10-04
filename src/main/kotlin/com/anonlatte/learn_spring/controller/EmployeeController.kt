package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.db.entity.Employee
import com.anonlatte.learn_spring.db.entity.User
import com.anonlatte.learn_spring.domain.repository.EmployeeRepository
import com.anonlatte.learn_spring.domain.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class EmployeeController @Autowired constructor(
    private val employeeRepository: EmployeeRepository,
    private val userRepository: UserRepository
) {

    private val logger = LoggerFactory.getLogger(EmployeeController::class.java)

    @Transactional
    @GetMapping("/employees")
    fun getEmployees(): ModelAndView {
        logger.info("/list -> connection; isAdmin: ${SecurityData.isAdmin}")
        val modelAndView = ModelAndView("list-employees")
        modelAndView.addObject(
            "employees", if (SecurityData.isAdmin) {
                employeeRepository.findAll()
            } else {
                getCurrentUser()?.employees.orEmpty()
            }
        )
        modelAndView.addObject("isAdmin", SecurityData.isAdmin)
        return modelAndView
    }


    @GetMapping("/employees/add")
    fun getEmployee(): ModelAndView {
        val modelAndView = ModelAndView("add-employee-form")
        val employee = Employee(users = setOfNotNull(getCurrentUser()))
        modelAndView.addObject("employee", employee)
        modelAndView.addObject("isAdmin", SecurityData.isAdmin)
        return modelAndView
    }

    @PostMapping("/employees/save")
    fun saveEmployee(@ModelAttribute employee: Employee): String {
        employeeRepository.save(employee)
        getCurrentUser()?.let {
            it.employees = it.employees.plus(employee)
            userRepository.save(it)
        }
        return "redirect:/employees"
    }

    @Transactional
    @GetMapping("/employees/update")
    fun getEmployee(@RequestParam employeeId: Long): ModelAndView {
        val modelAndView = ModelAndView("add-employee-form")
        val optionalEmployee = employeeRepository.findById(employeeId)
        val employee = if (optionalEmployee.isPresent) {
            optionalEmployee.get()
        } else {
            Employee(users = setOfNotNull(getCurrentUser()))
        }
        modelAndView.addObject("employee", employee)
        modelAndView.addObject("isAdmin", SecurityData.isAdmin)
        return modelAndView
    }

    @GetMapping("/employees/delete")
    fun deleteEmployee(@RequestParam employeeId: Long): String {
        employeeRepository.deleteById(employeeId)
        return "redirect:/employees"
    }

    private fun getCurrentUser(): User? {
        return SecurityData.currentAuth?.name.orEmpty().let {
            userRepository.findByEmail(it)
        }
    }
}

