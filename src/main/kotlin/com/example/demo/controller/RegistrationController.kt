package com.example.demo.controller

import com.example.demo.entity.Member
import com.example.demo.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegistrationController(private val memberRepository: MemberRepository, private val passwordEncoder: PasswordEncoder) {

    @GetMapping("/register")
    fun showRegistrationForm(model: Model): String {
        model.addAttribute("member", Member(username = "", password = ""))
        return "register"
    }

    @PostMapping("/register")
    fun registerMember(@ModelAttribute member: Member): String {
        val encryptedPassword = passwordEncoder.encode(member.password)
        val newMember = member.copy(password = encryptedPassword)
        memberRepository.save(newMember)
        return "redirect:/login"
    }
}
