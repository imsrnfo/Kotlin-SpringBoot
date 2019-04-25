package com.example.demo.configuration.security

import com.example.demo.models.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import java.io.Serializable

class UsuarioSession(username: String, password: String, authorities: Collection<GrantedAuthority>) : User(username, password, authorities), Serializable {
    var usuario: Usuario? = null
}
