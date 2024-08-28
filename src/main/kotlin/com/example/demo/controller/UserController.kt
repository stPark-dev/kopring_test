package com.example.demo

import com.example.demo.entity.User
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/hello")
    fun hello(@RequestParam(name = "name", required = false, defaultValue = "World") name: String): ModelAndView {
        val modelAndView = ModelAndView("hello")
        modelAndView.addObject("name", name)
        return modelAndView
    }

    @GetMapping
    fun getAllUsers(): Iterable<User> {
        return userService.findAll()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): User? {
        return userService.findById(id)
    }

    @PostMapping
    fun createUser(@RequestBody user: User): User {
        return userService.save(user)
    }

    @DeleteMapping
    fun deleteUser(@PathVariable id: Long){
        userService.deleteById(id)
    }

    @PutMapping("/{id}/email")
    fun updateEmail(@PathVariable id: Long, @RequestBody email: String): User? {
        userService.updateUserEmail(id, email)
        return userService.findById(id)
    }

    // 추가: 사용자 목록을 보여주는 HTML 템플릿 렌더링
    @GetMapping("/view")
    fun viewAllUsers(): ModelAndView {
        val users = userService.findAll()
        val modelAndView = ModelAndView("userList")
        modelAndView.addObject("users", users)
        return modelAndView
    }

    // 추가: 특정 사용자의 상세 정보를 보여주는 HTML 템플릿 렌더링
    @GetMapping("/view/{id}")
    fun viewUserById(@PathVariable id: Long): ModelAndView {
        val user = userService.findById(id)
        val modelAndView = ModelAndView("userDetails")
        modelAndView.addObject("user", user)
        return modelAndView
    }
}