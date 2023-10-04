package com.anonlatte.learn_spring.db.entity

import jakarta.persistence.*

@Entity
@Table(name = TableNames.EMPLOYEES)
class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "surname")
    var surname: String? = null,

    @Column(name = "department")
    var department: String? = null,

    @Column(name = "age")
    var age: Int? = null,

    @ManyToMany(mappedBy = "employees")
    var users: Set<User>? = null,
)

