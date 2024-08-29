package com.example.demo.service

import com.example.demo.entity.Member
import com.example.demo.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.User as SecurityUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User not found") }

        val authorities = listOf(SimpleGrantedAuthority(member.role))

        return SecurityUser(member.username, member.password, member.enabled, true, true, true, authorities)
    }

    fun findAll(): List<Member> {
        return memberRepository.findAll()
    }

    fun findById(id: Long): Member? {
        return memberRepository.findById(id).orElse(null)
    }

    // 추가된 save 메서드
    fun save(member: Member): Member {
        return memberRepository.save(member)
    }
}
