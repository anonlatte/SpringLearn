package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.entity.Student
import com.anonlatte.learn_spring.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MyController(
    @Autowired private val studentService: StudentService
) {

    @GetMapping("/students")
    fun getStudents(): List<Student> {
        return studentService.getStudents()
    }

    @GetMapping("/students/{studentId}")
    fun getStudent(@PathVariable studentId: Int): Student {
        return studentService.getStudent(studentId)
    }

    @PostMapping("/students")
    fun saveStudent(student: Student): Student {
        return studentService.saveStudent(student)
    }

    @PutMapping("/students")
    fun updateStudent(student: Student): Student {
        return studentService.saveStudent(student)
    }

    @DeleteMapping("/students/{studentId}")
    fun deleteStudent(@PathVariable studentId: Int): String {
        return studentService.deleteStudent(studentId)
    }
}