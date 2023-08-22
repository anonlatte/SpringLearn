package com.anonlatte.learn_spring.model


data class Response(
    val uid: String,
    val operationUid: String,
    val systemTime: String,
    val code: String,
    val errorCode: String,
    val errorMessage: String,
)