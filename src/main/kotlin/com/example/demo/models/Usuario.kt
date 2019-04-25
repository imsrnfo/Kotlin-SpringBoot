package com.example.demo.models

import javax.persistence.*

@Entity
@Table(name = "usuario")
class Usuario(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var username: String? = null,
        var password: String? = null

        )