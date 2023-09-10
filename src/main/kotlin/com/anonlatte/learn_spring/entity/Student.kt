package com.anonlatte.learn_spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "STUDENTS")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "name")
    val name: String = "",
    @Column(name = "surname")
    val surname: String = "",
    @Column(name = "faculty")
    val faculty: String = "",
    @Column(name = "age")
    val age: Int = 0
)

