package com.example.app_phonecare.phonecare.Api

data class ApiResponse(
    val success: Boolean,
    val status_code: Int,
    val message: String,
    val data: List<DataUsuarios>
)
