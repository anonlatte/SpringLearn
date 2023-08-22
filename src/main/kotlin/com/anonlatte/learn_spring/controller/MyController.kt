package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.model.Request
import com.anonlatte.learn_spring.model.Response
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController {

    @PostMapping("/feedback")
    fun feedback(@RequestBody request: Request): Response {
        return Response(
            uid = request.uid,
            operationUid = request.operationUid,
            systemTime = request.systemTime,
            code = "success",
            errorCode = "",
            errorMessage = "",
        )
    }
}