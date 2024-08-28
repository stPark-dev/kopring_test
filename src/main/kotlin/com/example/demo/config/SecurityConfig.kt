package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/login", "/resources/**", "/error", "/users/hello").permitAll() // 로그인 페이지와 정적 리소스, 오류 페이지는 모두 접근 가능
                    .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
            }
            .formLogin { login ->
                login
                    .loginPage("/login") // 커스텀 로그인 페이지 경로
                    .defaultSuccessUrl("/users/view", true) // 로그인 성공 시 이동할 경로
                    .permitAll()
            }
            .logout { logout ->
                logout.permitAll()
            }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()

        val admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }
}