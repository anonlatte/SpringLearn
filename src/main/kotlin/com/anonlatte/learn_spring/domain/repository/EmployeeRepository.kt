package com.anonlatte.learn_spring.domain.repository

import com.anonlatte.learn_spring.db.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>