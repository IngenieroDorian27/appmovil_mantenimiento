package com.example.app_phonecare.phonecare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_phonecare.R
import com.example.app_phonecare.phonecare.Administrador.MenuActivity
import com.example.app_phonecare.phonecare.Api.DataPhone
import com.example.app_phonecare.phonecare.Api.RetrofitInstance
import com.example.app_phonecare.phonecare.Cliente.ClienteActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContra: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        etUsuario = findViewById(R.id.etUsuario)
        etContra = findViewById(R.id.etContra)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContra.text.toString()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                login(usuario, contrasena)
            } else {
                Toast.makeText(this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun login(usuario: String, contrasena: String) {

        val loginRequest = DataPhone(usuario = usuario, contrasena = contrasena)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.validateLogin(loginRequest)
                val tipoUsuario = response.tipoUsuario
                val marcaTelUsuario = response.marcaTelUsuario
                val requerimientoUsuario = response.requerimientoUsuario
                val accesoriosUsuario = response.accesoriosUsuario
                val estadoEquipoUsuario = response.estadoEquipoUsuario
                val id = response.idUsuario

                withContext(Dispatchers.Main) {

                    if (response.message?.success == true) {
                        Toast.makeText(this@LoginActivity, "Bienvenido $tipoUsuario", Toast.LENGTH_SHORT).show()

                        if (response.tipoUsuario == "administrador") {

                            val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else if (response.tipoUsuario == "cliente") {

                            val intent = Intent(this@LoginActivity, ClienteActivity::class.java)
                            intent.putExtra("marcaTelUsuario", marcaTelUsuario)
                            intent.putExtra("requerimientoUsuario", requerimientoUsuario)
                            intent.putExtra("accesoriosUsuario", accesoriosUsuario)
                            intent.putExtra("estadoEquipoUsuario", estadoEquipoUsuario)
                            intent.putExtra("id", id)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Tipo de usuario desconocido", Toast.LENGTH_SHORT).show()
                        }
                    } else {

                        Toast.makeText(this@LoginActivity, "Error: ${response.message?.data}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: HttpException) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Error, usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}