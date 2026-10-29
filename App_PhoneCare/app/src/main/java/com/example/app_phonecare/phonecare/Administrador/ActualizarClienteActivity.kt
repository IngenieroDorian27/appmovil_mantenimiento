package com.example.app_phonecare.phonecare.Administrador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_phonecare.R
import com.example.app_phonecare.phonecare.Api.DataActualizar
import com.example.app_phonecare.phonecare.Api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActualizarClienteActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCedula: EditText
    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etWhatsapp: EditText
    private lateinit var etMarca: EditText
    private lateinit var btnActualizar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_actualizar_cliente)

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        etCedula = findViewById(R.id.etCedula)
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etWhatsapp = findViewById(R.id.etWhatsapp)
        etMarca = findViewById(R.id.etMarca)
        btnActualizar = findViewById(R.id.btnActualizar)

        val usuario: String = intent.extras?.getString("user").orEmpty()
        val password: String = intent.extras?.getString("pasword").orEmpty()
        val cedula: String = intent.extras?.getString("cedula").orEmpty()
        val nombre: String = intent.extras?.getString("nombre").orEmpty()
        val correo: String = intent.extras?.getString("correo").orEmpty()
        val whatsapp: String = intent.extras?.getString("whatsapp").orEmpty()
        val marca: String = intent.extras?.getString("marca").orEmpty()

        etUsuario.setText(usuario)
        etPassword.setText(password)
        etCedula.setText(cedula)
        etNombre.setText(nombre)
        etCorreo.setText(correo)
        etWhatsapp.setText(whatsapp)
        etMarca.setText(marca)

        btnActualizar.setOnClickListener {
            updateCliente()
        }
    }

    private fun updateCliente() {
        val usuario = etUsuario.text.toString()
        val password = etPassword.text.toString()
        val cedula = etCedula.text.toString()
        val nombre = etNombre.text.toString()
        val correo = etCorreo.text.toString()
        val whatsapp = etWhatsapp.text.toString()
        val marca = etMarca.text.toString()
        val id : Int = intent.extras?.getInt("id") ?: 0

        if (usuario.isBlank() || password.isBlank() || cedula.isBlank() ||
            nombre.isBlank() || correo.isBlank() || whatsapp.isBlank() || marca.isBlank()
        ) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()

        }else {
            val updateRequest = DataActualizar(usuario, password, cedula, nombre, correo, whatsapp, marca)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = RetrofitInstance.api.actualizarCliente(id, updateRequest)

                    if(response.success){
                        Toast.makeText(this@ActualizarClienteActivity, "Cliente actualizado con éxito", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@ActualizarClienteActivity, MenuActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@ActualizarClienteActivity, "Error al actualizar cliente: ${response.data}", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@ActualizarClienteActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

}