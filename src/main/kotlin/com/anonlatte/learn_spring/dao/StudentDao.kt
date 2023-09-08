package com.anonlatte.learn_spring.dao

import com.anonlatte.learn_spring.entity.Student
import org.springframework.stereotype.Repository

@Repository
interface StudentDao {
    fun getStudents(): List<Student>
    fun getStudent(studentId: Int): Student
    fun saveStudent(student: Student): Student
    fun deleteStudent(studentId: Int): String
}

