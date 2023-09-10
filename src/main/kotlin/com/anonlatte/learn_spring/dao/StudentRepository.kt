package com.anonlatte.learn_spring.dao

import com.anonlatte.learn_spring.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>