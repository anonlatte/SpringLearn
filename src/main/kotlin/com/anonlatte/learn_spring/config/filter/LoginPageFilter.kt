package com.anonlatte.learn_spring.config.filter

import com.anonlatte.learn_spring.db.entity.RoleNames
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class LoginPageFilter : GenericFilterBean() {

    private val authRole: Collection<GrantedAuthority>
        get() = SecurityContextHolder.getContext().authentication?.authorities.orEmpty()

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        val uri = req.requestURI
        val redirectRequired = Regex("(/login)|(/register(/.*)?)|(/index)|(/)").matches(uri)
                && authRole.isNotEmpty()
        if (redirectRequired) {
            val redirectUrl = when (authRole.first().authority) {
                RoleNames.ROLE_ADMIN -> "/users"
                RoleNames.ROLE_USER -> "/students"
                else -> "/"
            }
            val encodeRedirectURL = res.encodeRedirectURL(req.contextPath + redirectUrl)
            res.status = HttpServletResponse.SC_TEMPORARY_REDIRECT
            res.setHeader("Location", encodeRedirectURL)
        }
        chain.doFilter(request, response)
    }
}