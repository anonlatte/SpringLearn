package com.anonlatte.learn_spring.domain.repository

import com.anonlatte.learn_spring.db.entity.UserLog
import org.springframework.data.jpa.repository.JpaRepository

interface UserLogRepository : JpaRepository<UserLog, Long>