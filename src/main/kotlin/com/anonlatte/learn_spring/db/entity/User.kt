package com.anonlatte.learn_spring.db.entity

import com.anonlatte.learn_spring.dto.UserDto
import jakarta.persistence.*

@Entity
@Table(name = TableNames.USERS)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = TableNames.USER_ROLES,
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role>,

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = TableNames.USERS_EMPLOYEES,
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "employee_id")]
    )
    var employees: Set<Employee>,
) {

    companion object {

        fun User.toDto() = UserDto(
            id = id,
            firstName = name.split(" ")[0],
            lastName = name.split(" ")[1],
            email = email,
            password = password
        )
    }
}