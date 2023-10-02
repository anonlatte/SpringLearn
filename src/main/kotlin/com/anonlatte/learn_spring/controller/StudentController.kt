package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.db.entity.Student
import com.anonlatte.learn_spring.domain.repository.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class StudentController(
    @Autowired private val studentRepository: StudentRepository
) {

    private val logger = LoggerFactory.getLogger(StudentController::class.java)

    @GetMapping("/list", "/")
    fun getStudents(): ModelAndView {
        logger.info("/list -> connection")
        val modelAndView = ModelAndView("list-students")
        modelAndView.addObject("students", studentRepository.findAll())
        return modelAndView
    }

    @GetMapping("/addStudentForm")
    fun getStudent(): ModelAndView {
        val modelAndView = ModelAndView("add-student-form")
        val student = Student()
        modelAndView.addObject("student", student)
        return modelAndView
    }

    @PostMapping("/saveStudent")
    fun saveStudent(@ModelAttribute student: Student): String {
        studentRepository.save(student)
        return "redirect:/list"
    }

    @GetMapping("/showUpdateForm")
    fun getStudent(@RequestParam studentId: Long): ModelAndView {
        val modelAndView = ModelAndView("add-student-form")
        val optionalStudent = studentRepository.findById(studentId)
        val student = if (optionalStudent.isPresent) {
            optionalStudent.get()
        } else {
            Student()
        }
        modelAndView.addObject("student", student)
        return modelAndView
    }

    @GetMapping("/deleteStudent")
    fun deleteStudent(@RequestParam studentId: Long): String {
        studentRepository.deleteById(studentId)
        return "redirect:/list"
    }
}

