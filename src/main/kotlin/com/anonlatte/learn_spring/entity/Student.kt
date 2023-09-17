package com.anonlatte.learn_spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "STUDENTS")
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,
    @Column(name = "name")
    var name: String = "",
    @Column(name = "surname")
    var surname: String = "",
    @Column(name = "faculty")
    var faculty: String = "",
    @Column(name = "age")
    var age: Int = 0,
)