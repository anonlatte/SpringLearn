package com.anonlatte.learn_spring.config.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class LoginPageFilter : GenericFilterBean() {

    private val isAuthenticated: Boolean
        get() = SecurityContextHolder.getContext().authentication?.isAuthenticated == true

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        val uri = req.requestURI
        val redirectRequired = Regex("(/login)|(/register(/.*)?)").matches(uri) && isAuthenticated
        if (redirectRequired) {
            val encodeRedirectURL = res.encodeRedirectURL(req.contextPath + "/")
            res.status = HttpServletResponse.SC_TEMPORARY_REDIRECT
            res.setHeader("Location", encodeRedirectURL)
        }
        chain.doFilter(request, response)
    }
}