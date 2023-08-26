package com.anonlatte.learn_spring.service

import com.anonlatte.learn_spring.model.Request
import com.anonlatte.learn_spring.model.Response
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.atomic.AtomicBoolean

@Service
class ModifyRequestSystemTime : ModifyRequestService {

    override fun modifyRequest(request: Request) {
        if (RequestModificationHandler.wasRequestModified.get()) {
            RequestModificationHandler.wasRequestModified.set(false)
            return
        } else {
            RequestModificationHandler.wasRequestModified.set(true)
        }

        val modifiedRequest = request.copy(
            systemTime = "test"
        )

        val httpEntity = HttpEntity(modifiedRequest)
        RestTemplate().exchange(
            "http://localhost:8082/feedback",
            HttpMethod.POST,
            httpEntity,
            Response::class.java
        )
    }
}

/* В текущей реализации по методичке вызов RestTemplate().exchange создает бесконечный цикл вызова /feedback */
object RequestModificationHandler {
    val wasRequestModified = AtomicBoolean(false)
}