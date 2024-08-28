package com.example.demo

import com.example.demo.entity.User
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserApiController(private val userService: UserService) {

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

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteById(id)
    }

    @PutMapping("/{id}/email")
    fun updateEmail(@PathVariable id: Long, @RequestBody email: String): User? {
        userService.updateUserEmail(id, email)
        return userService.findById(id)
    }
}
