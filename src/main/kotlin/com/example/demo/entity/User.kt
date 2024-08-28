package com.example.demo.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // "id" 컬럼에 매핑
    val id: Long = 0,

    @Column(name = "name") // "name" 컬럼에 매핑
    val name: String?,

    @Column(name = "email") // "email" 컬럼에 매핑
    val email: String?
)