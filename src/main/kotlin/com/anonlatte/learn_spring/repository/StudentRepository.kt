package com.anonlatte.learn_spring.repository

import com.anonlatte.learn_spring.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>