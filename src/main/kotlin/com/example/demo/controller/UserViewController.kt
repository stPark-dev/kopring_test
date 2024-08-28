package com.example.demo

import com.example.demo.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/users")
class UserViewController(private val userService: UserService) {

    @GetMapping("/hello")
    fun hello(@RequestParam(name = "name", required = false, defaultValue = "World") name: String): ModelAndView {
        val modelAndView = ModelAndView("hello")
        modelAndView.addObject("name", name)
        return modelAndView
    }

    // 사용자 목록을 보여주는 HTML 템플릿 렌더링
    @GetMapping("/view")
    fun viewAllUsers(): ModelAndView {
        val users = userService.findAll()
        val modelAndView = ModelAndView("userList")
        modelAndView.addObject("users", users)
        return modelAndView
    }

    // 특정 사용자의 상세 정보를 보여주는 HTML 템플릿 렌더링
    @GetMapping("/view/{id}")
    fun viewUserById(@PathVariable id: Long): ModelAndView {
        val user = userService.findById(id)
        val modelAndView = ModelAndView("userDetails")
        modelAndView.addObject("user", user)
        return modelAndView
    }
}
