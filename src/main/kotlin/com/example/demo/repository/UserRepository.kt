package com.example.demo.repository

import com.example.demo.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository :CrudRepository<User, Long>