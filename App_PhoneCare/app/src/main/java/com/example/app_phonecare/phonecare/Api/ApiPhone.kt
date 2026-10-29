package com.example.app_phonecare.phonecare.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiPhone {
    @POST("validacionlogin")
    suspend fun validateLogin(@Body loginRequest: DataPhone): DataPhone

    @GET("usuarios")
    suspend fun getUsuarios(): UsuariosResponse

    @POST("crearCliente")
    suspend fun crearCliente(@Body clienteRequest: DataRegistro): RegistroResponse

    @PUT("actualizarCliente/{id}")
    suspend fun actualizarCliente(@Path("id") id: Int, @Body clienteRequest: DataActualizar): ActualizarResponse

    @PUT("actualizarMantenimiento/{id}")
    suspend fun actualizarMant(@Path("id") id: Int, @Body clienteRequest: DataMant): MantResponse

    @PUT("actualizarComentario/{id}")
    suspend fun actualizarComentario(@Path("id") id: Int, @Body clienteRequest: DataComentario): ComentarioResponse

    @DELETE("eliminarCliente/{id}")
    suspend fun eliminarCliente(@Path("id") id: Int): EliminarResponse


}

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:3200/"

    val api: ApiPhone by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPhone::class.java)
    }

}