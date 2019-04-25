package com.example.demo.configuration.security

import com.example.demo.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.HashSet

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var usuarioService: UsuarioService

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        try {
            val user = usuarioService.obtenerPorUsername(username)
            val grantedAuthorities = HashSet<GrantedAuthority>()
            grantedAuthorities.add(SimpleGrantedAuthority("ROLE_USER"))
            val result = UsuarioSession(user.username!!, "{noop}" + user.password, grantedAuthorities)
            result.usuario = user
            return result
        } catch (e: Exception) {
            throw UsernameNotFoundException("Error al loguearse", e)
        }

    }
}