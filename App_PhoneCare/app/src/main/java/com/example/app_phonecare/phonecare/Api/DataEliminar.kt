package com.example.app_phonecare.phonecare.Api

data class EliminarResponse(
    val success: Boolean,
    val status_code: Int,
    val message: String,
    val data: DataEliminar
)

data class DataEliminar(
    val fieldCount: Int,
    val affectedRows: Int,
    val insertId: Int,
    val info: String,
    val serverStatus: Int,
    val warningStatus: Int,
    val changedRows: Int
)
