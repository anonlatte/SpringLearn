package com.anonlatte.learn_spring.dao

import com.anonlatte.learn_spring.entity.Student
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class StudentDaoImpl(
    @Autowired private val entityManager: EntityManager
) : StudentDao {
    override fun getStudents(): List<Student> {
        val query = entityManager.createQuery("from Student", Student::class.java)
        return query.resultList
    }

    override fun getStudent(studentId: Int): Student {
        return entityManager.find(Student::class.java, studentId)
    }

    override fun saveStudent(student: Student): Student {
        entityManager.merge(student)
        return student
    }

    override fun deleteStudent(studentId: Int): String {
        val student = entityManager.find(Student::class.java, studentId)
        entityManager.remove(student)
        return "Student deleted"
    }
}