package com.example.app_phonecare.phonecare.Administrador

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_phonecare.R
import com.example.app_phonecare.phonecare.Api.DataRegistro
import com.example.app_phonecare.phonecare.Api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoClienteActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCedula: EditText
    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etWhatsapp: EditText
    private lateinit var etMarca: EditText
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nuevo_cliente)

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        etCedula = findViewById(R.id.etCedula)
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etWhatsapp = findViewById(R.id.etWhatsapp)
        etMarca = findViewById(R.id.etMarca)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            registrarCliente()
        }

    }

    private fun registrarCliente() {
        val usuario = etUsuario.text.toString()
        val password = etPassword.text.toString()
        val cedula = etCedula.text.toString()
        val nombre = etNombre.text.toString()
        val correo = etCorreo.text.toString()
        val whatsapp = etWhatsapp.text.toString()
        val marcaTelefono = etMarca.text.toString()

        if (usuario.isEmpty() || password.isEmpty() || cedula.isEmpty() || nombre.isEmpty() ||
            correo.isEmpty() || whatsapp.isEmpty() || marcaTelefono.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show()
        }else {
            val clienteRequest = DataRegistro(usuario, password, cedula, nombre, correo, whatsapp, marcaTelefono)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = RetrofitInstance.api.crearCliente(clienteRequest)

                    if (response.success) {
                        Toast.makeText(this@NuevoClienteActivity, "Cliente registrado con éxito", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@NuevoClienteActivity, "Error al registrar cliente: ${response.data}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@NuevoClienteActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}