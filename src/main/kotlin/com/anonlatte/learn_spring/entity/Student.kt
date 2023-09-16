package com.anonlatte.learn_spring.entity

import jakarta.persistence.*

@Entity
@Table(name = TableNames.STUDENTS)
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "surname")
    var surname: String? = null,

    @Column(name = "faculty")
    var faculty: String? = null,

    @Column(name = "age")
    var age: Int? = null,
)

