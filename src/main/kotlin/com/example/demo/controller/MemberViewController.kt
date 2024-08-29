package com.example.demo.controller

import com.example.demo.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/members")
class MemberViewController(private val memberService: MemberService) {

    @GetMapping("/hello")
    fun hello(): ModelAndView {
        val modelAndView = ModelAndView("hello")
        modelAndView.addObject("name", "World")
        return modelAndView
    }

    // 회원 목록을 보여주는 HTML 템플릿 렌더링
    @GetMapping("/view")
    fun viewAllMembers(): ModelAndView {
        val members = memberService.findAll()
        val modelAndView = ModelAndView("memberList")
        modelAndView.addObject("members", members)
        return modelAndView
    }

    // 특정 회원의 상세 정보를 보여주는 HTML 템플릿 렌더링
    @GetMapping("/view/{id}")
    fun viewMemberById(@PathVariable id: Long): ModelAndView {
        val member = memberService.findById(id)
        val modelAndView = ModelAndView("memberDetails")
        modelAndView.addObject("member", member)
        return modelAndView
    }
}
