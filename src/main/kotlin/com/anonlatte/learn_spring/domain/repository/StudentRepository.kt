package com.anonlatte.learn_spring.domain.repository

import com.anonlatte.learn_spring.db.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>