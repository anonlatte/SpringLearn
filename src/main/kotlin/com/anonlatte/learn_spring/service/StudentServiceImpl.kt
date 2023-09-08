package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.dao.StudentDao
import com.anonlatte.learn_spring.entity.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl(
    @Autowired private val studentDao: StudentDao
) : StudentService {

    @Transactional
    override fun getStudents(): List<Student> {
        return studentDao.getStudents()
    }

    @Transactional
    override fun getStudent(studentId: Int): Student {
        return studentDao.getStudent(studentId)
    }

    @Transactional
    override fun saveStudent(student: Student): Student {
        return studentDao.saveStudent(student)
    }

    @Transactional
    override fun deleteStudent(studentId: Int): String {
        return studentDao.deleteStudent(studentId)
    }
}