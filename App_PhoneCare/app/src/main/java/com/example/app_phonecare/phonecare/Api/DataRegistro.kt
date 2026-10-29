package com.example.app_phonecare.phonecare.Api

data class RegistroResponse(
    val success: Boolean,
    val status_code: Int,
    val data: String
)

data class DataRegistro(
    val USUARIO: String,
    val PASWORD: String,
    val CEDULA: String,
    val NOMBRE: String,
    val CORREO: String,
    val WHATSAPP: String,
    val MARCA_TELEFONO: String
)

