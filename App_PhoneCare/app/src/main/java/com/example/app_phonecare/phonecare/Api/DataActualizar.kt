package com.example.app_phonecare.phonecare.Api

import com.google.gson.JsonElement

data class ActualizarResponse(
    val success: Boolean,
    val status_code: Int,
    val data: JsonElement
)

data class DataActualizar(
    val USUARIO: String,
    val PASWORD: String,
    val CEDULA: String,
    val NOMBRE: String,
    val CORREO: String,
    val WHATSAPP: String,
    val MARCA_TELEFONO: String
)
