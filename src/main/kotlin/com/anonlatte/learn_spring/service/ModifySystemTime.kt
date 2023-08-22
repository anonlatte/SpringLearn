package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.model.Response
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("ModifySystemTime")
class ModifySystemTime : MyModifyService {

    override fun modify(response: Response): Response {
        return response.copy(systemTime = "")
    }
}