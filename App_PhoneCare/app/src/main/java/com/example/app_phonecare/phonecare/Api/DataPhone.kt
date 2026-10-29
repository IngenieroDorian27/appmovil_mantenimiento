package com.example.app_phonecare.phonecare.Api


data class Message(
    val success: Boolean,
    val status_code: Int,
    val data: String
)


data class DataPhone(
    val usuario: String,
    val contrasena: String,
    val message: Message? = null,
    val tipoUsuario: String? = null,
    val idUsuario: Int? = null,
    val requerimientoUsuario: String? = null,
    val accesoriosUsuario: String? = null,
    val estadoEquipoUsuario: String? = null,
    val pagoUsuario: String? = null,
    val nombreUsuario: String? = null,
    val marcaTelUsuario: String? = null,
    val comentario: String? = null

)
