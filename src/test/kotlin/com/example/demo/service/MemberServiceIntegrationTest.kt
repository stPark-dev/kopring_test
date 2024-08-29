package com.example.demo.service

import com.example.demo.entity.Member
import com.example.demo.repository.MemberRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional // 테스트가 끝나면 DB 변경사항을 롤백합니다.
class MemberServiceIntegrationTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {

    @Test
    fun `should save and retrieve a member`() {
        // Given
        val member = Member(username = "john_doe", password = "password123", email = "aaaa@abc.com")

        // When
        val savedMember = memberService.save(member)
        val foundMember = memberService.findById(savedMember.id)

        // Then
        assertNotNull(foundMember)
        assertEquals(savedMember.username, foundMember?.username)
    }

    @Test
    fun `should find all members`() {
        // Given
        memberRepository.save(Member(username = "jane_doe", password = "password456", email = "bbbbb@abc.com"))
        memberRepository.save(Member(username = "john_doe", password = "password123", email = "ccccccc@abc.com"))

        // When
        val members = memberService.findAll()

        // Then
        assertEquals(2, members.size)
    }
}