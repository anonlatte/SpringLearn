package com.anonlatte.learn_spring.db.entity

import jakarta.persistence.*

@Entity
@Table(name = TableNames.USERS_LOGS)
class UserLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "user_id")
    val user: String,

    @Column(name = "action")
    val action: String,

    @Column(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
)
