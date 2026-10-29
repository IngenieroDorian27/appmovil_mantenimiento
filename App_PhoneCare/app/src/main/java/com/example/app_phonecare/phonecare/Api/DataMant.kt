package com.example.app_phonecare.phonecare.Api

import com.google.gson.JsonElement

data class MantResponse(
    val success: Boolean,
    val status_code: Int,
    val data: JsonElement
)

data class DataMant(
    val REQUERIMIENTO: String,
    val ACCESORIOS: String,
    val ESTADO_EQUIPO: String,
    val PAGO: String
)
