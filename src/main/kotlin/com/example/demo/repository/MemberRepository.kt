package com.example.demo.repository

import com.example.demo.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsername(username: String): Optional<Member>
}