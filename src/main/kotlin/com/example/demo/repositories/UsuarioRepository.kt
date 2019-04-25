package com.example.demo.repositories

import com.example.demo.models.Usuario
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UsuarioRepository : CrudRepository<Usuario, Long> {

    @Query("SELECT o FROM Usuario o WHERE o.username = :username")
    fun obtenerPorUsername(@Param("username") username: String): Usuario

}