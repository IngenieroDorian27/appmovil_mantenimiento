package com.example.app_phonecare.phonecare.Api

data class UsuariosResponse(
    val success: Boolean,
    val status_code: Int,
    val message: String,
    val data: List<DataUsuarios>
)

data class DataUsuarios(
    val ID_USER: Int,
    val USUARIO: String,
    val PASWORD: String,
    val TIPO_USER: String,
    val CEDULA: String,
    val NOMBRE: String,
    val CORREO: String,
    val WHATSAPP: String,
    val MARCA_TELEFONO: String,
    val REQUERIMIENTO: String,
    val ACCESORIOS: String,
    val ESTADO_EQUIPO: String,
    val PAGO: String,
    val COMENTARIO: String
)
