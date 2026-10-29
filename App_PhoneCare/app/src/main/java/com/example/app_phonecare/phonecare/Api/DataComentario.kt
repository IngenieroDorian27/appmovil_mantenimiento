package com.example.app_phonecare.phonecare.Api

import com.google.gson.JsonElement


data class ComentarioResponse(
    val success: Boolean,
    val status_code: Int,
    val data: JsonElement
)

data class DataComentario(
    val COMENTARIO: String
)
