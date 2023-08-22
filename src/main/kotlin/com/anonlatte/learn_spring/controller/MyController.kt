package com.anonlatte.learn_spring.controller

import com.anonlatte.learn_spring.model.Request
import com.anonlatte.learn_spring.model.Response
import com.anonlatte.learn_spring.service.MyModifyService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(
    @Qualifier("ModifyErrorMessage")
    private val myModifyService: MyModifyService
) {

    private val logger = LoggerFactory.getLogger(MyController::class.java)

    @PostMapping("/feedback")
    fun feedback(@RequestBody request: Request): ResponseEntity<Response> {
        logger.info("Входящий request: $request")

        val response = Response(
            uid = request.uid,
            operationUid = request.operationUid,
            systemTime = request.systemTime,
            code = "success",
            errorCode = "",
            errorMessage = "",
        )

        val modifiedResponse = myModifyService.modify(response)

        logger.info("Исходящий response: $modifiedResponse")

        return ResponseEntity.ok(modifiedResponse)
    }
}