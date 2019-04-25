package com.example.demo.services

import com.example.demo.models.Usuario
import com.example.demo.repositories.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    fun obtenerPorUsername(username:String) : Usuario = usuarioRepository.obtenerPorUsername(username)

}