package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.entity.Student
import org.springframework.stereotype.Service

@Service
interface StudentService {
    fun getStudents(): List<Student>
    fun getStudent(studentId: Int): Student
    fun saveStudent(student: Student): Student
    fun deleteStudent(studentId: Int): String
}

