// src/test/kotlin/com/example/demo/service/MemberServiceTest.kt
package com.example.demo.service

import com.example.demo.entity.Member
import com.example.demo.repository.MemberRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.Optional

@SpringBootTest
class MemberServiceTest {

    private val memberRepository: MemberRepository = mockk()
    private val passwordEncoder: PasswordEncoder = mockk()
    private val memberService: MemberService = MemberService(memberRepository)

    @Test
    fun `should return all members`() {
        // Given
        val members = listOf(
            Member(id = 1L, username = "john_doe", password = "encodedPassword1", email = "john@example.com"),
            Member(id = 2L, username = "jane_doe", password = "encodedPassword2", email = "jane@example.com")
        )
        every { memberRepository.findAll() } returns members

        // When
        val result = memberService.findAll()

        // Then
        assertEquals(2, result.size)
        assertEquals("john_doe", result.first().username)
    }

    @Test
    fun `should return member by id`() {
        // Given
        val member = Member(id = 1L, username = "john_doe", password = "encodedPassword1", email = "john@example.com")
        every { memberRepository.findById(1L) } returns Optional.of(member)

        // When
        val result = memberService.findById(1L)

        // Then
        assertNotNull(result)
        assertEquals("john_doe", result?.username)
    }

    @Test
    fun `should load user by username`() {
        // Given
        val member = Member(id = 1L, username = "john_doe", password = "encodedPassword1", email = "john@example.com")
        every { memberRepository.findByUsername("john_doe") } returns Optional.of(member)

        // When
        val userDetails = memberService.loadUserByUsername("john_doe")

        // Then
        assertNotNull(userDetails)
        assertEquals("john_doe", userDetails.username)
        assertEquals("encodedPassword1", userDetails.password)
    }

    @Test
    fun `should throw UsernameNotFoundException when user not found`() {
        // Given
        every { memberRepository.findByUsername("non_existent_user") } returns Optional.empty()

        // When / Then
        assertThrows(UsernameNotFoundException::class.java) {
            memberService.loadUserByUsername("non_existent_user")
        }
    }
}
