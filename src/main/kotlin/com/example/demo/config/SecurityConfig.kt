package com.example.demo.config

import com.example.demo.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(private val memberService: MemberService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/login", "/register", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll() // 모든 정적 리소스 접근 허용
                    .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
            }
            .formLogin { login ->
                login
                    .loginPage("/login") // 커스텀 로그인 페이지 설정
                    .defaultSuccessUrl("/members/view", true) // 로그인 성공 시 리다이렉트 경로 설정
                    .failureUrl("/login?error=true") // 로그인 실패 시 리다이렉트 경로 설정
                    .permitAll()
            }
            .logout { logout ->
                logout
                    .logoutUrl("/logout") // 로그아웃 경로 설정
                    .logoutSuccessUrl("/login?logout=true") // 로그아웃 성공 시 리다이렉트 경로 설정
                    .invalidateHttpSession(true) // 세션 무효화
                    .deleteCookies("JSESSIONID") // 쿠키 삭제
                    .permitAll()
            }
            .userDetailsService(memberService) // 커스텀 UserDetailsService 설정
            .csrf { csrf ->
                csrf.disable() // 필요시 CSRF 보호 비활성화 (테스트용)
            }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
