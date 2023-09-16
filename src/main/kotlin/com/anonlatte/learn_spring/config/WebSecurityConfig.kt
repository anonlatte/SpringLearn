package com.anonlatte.learn_spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector


@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    companion object {

        @Bean
        fun passwordEncoder() = BCryptPasswordEncoder()
    }

    @Bean
    fun mvc(introspector: HandlerMappingIntrospector?): MvcRequestMatcher.Builder {
        return MvcRequestMatcher.Builder(introspector)
    }

    @Bean
    fun filterChain(http: HttpSecurity, mvc: MvcRequestMatcher.Builder): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(antMatcher("/register/**")).permitAll()
                    .requestMatchers(mvc.pattern("/index")).permitAll()
                    .requestMatchers(mvc.pattern("/list")).hasRole("ADMIN")
                    .requestMatchers(mvc.pattern("/users")).hasRole("ADMIN")
            }
            .formLogin {
                it.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/users")
                    .permitAll()
            }
            .logout {
                it.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                    .permitAll()
            }

        return http.build()
    }
}