package com.anonlatte.learn_spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "ROLES")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "name", nullable = false, unique = true)
    val name: String = "",
    @ManyToMany(mappedBy = "roles")
    val users: List<User> = emptyList()
)