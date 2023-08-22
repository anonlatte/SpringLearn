package com.anonlatte.learn_spring.model

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class Request(
    @NotBlank
    @Size(max = 32)
    val uid: String,
    @NotBlank
    @Size(max = 32)
    val operationUid: String,
    val systemName: String,
    @NotBlank
    val systemTime: String,
    val source: String,
    val communicationId: Int,
    val templateId: Int,
    @Max(7)
    val productCode: Int,
    @Max(5)
    val smsCode: Int,
)
