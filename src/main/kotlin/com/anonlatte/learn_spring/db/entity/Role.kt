package com.anonlatte.learn_spring.db.entity

import jakarta.persistence.*

@Entity
@Table(name = TableNames.ROLES)
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @ManyToMany(mappedBy = "roles")
    var users: Set<User>? = null,
)