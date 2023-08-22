package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.model.Response

interface MyModifyService {

    fun modify(response: Response): Response
}

