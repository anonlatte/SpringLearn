package com.anonlatte.learn_spring.config

import com.anonlatte.learn_spring.config.filter.LoginPageFilter
import com.anonlatte.learn_spring.db.entity.RoleNames
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher
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
            .addFilterAfter(LoginPageFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests {
                it.requestMatchers(antMatcher("/register/**")).permitAll()
                    .requestMatchers(regexMatcher("(/index)|(/)")).permitAll()
                    .requestMatchers(regexMatcher("/users")).hasAuthority(RoleNames.ROLE_ADMIN)
                    .requestMatchers(antMatcher("/students/**")).hasAnyRole(RoleNames.ROLE_ADMIN, RoleNames.ROLE_USER)
                    .anyRequest().hasAnyAuthority(RoleNames.ROLE_ADMIN, RoleNames.ROLE_USER)
            }
            .formLogin {
                it.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
            }
            .logout {
                it.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                    .permitAll()
            }

        return http.build()
    }
}