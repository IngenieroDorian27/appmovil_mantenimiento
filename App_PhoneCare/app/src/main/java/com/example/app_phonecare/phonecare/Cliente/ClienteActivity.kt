package com.example.app_phonecare.phonecare.Cliente

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_phonecare.R
import com.example.app_phonecare.phonecare.Api.DataComentario
import com.example.app_phonecare.phonecare.Api.DataPhone
import com.example.app_phonecare.phonecare.Api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClienteActivity : AppCompatActivity() {

    private lateinit var tvTelefono: TextView
    private lateinit var tvRequisito: TextView
    private lateinit var tvAccesorios: TextView
    private lateinit var tvEstado: TextView
    private lateinit var etComentario: EditText
    private lateinit var btnComentario: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cliente)

        tvTelefono = findViewById(R.id.tvTelefono)
        tvRequisito = findViewById(R.id.tvRequisito)
        tvAccesorios = findViewById(R.id.tvAccesorios)
        tvEstado = findViewById(R.id.tvEstado)
        etComentario = findViewById(R.id.etComentario)
        btnComentario = findViewById(R.id.btnComentario)

        val marcaTelUsuario: String = intent.extras?.getString("marcaTelUsuario").orEmpty()
        val requerimientoUsuario: String = intent.extras?.getString("requerimientoUsuario").orEmpty()
        val accesoriosUsuario: String = intent.extras?.getString("accesoriosUsuario").orEmpty()
        val estadoEquipoUsuario: String = intent.extras?.getString("estadoEquipoUsuario").orEmpty()

        tvTelefono.text = "Telefono: $marcaTelUsuario"
        tvRequisito.text = "Requisito: $requerimientoUsuario"
        tvAccesorios.text = "Accesorios: $accesoriosUsuario"
        tvEstado.text = "Estado: $estadoEquipoUsuario"

        btnComentario.setOnClickListener {
            comentario()
        }


    }

    private fun comentario() {
        val comentario = etComentario.text.toString()
        val id = intent.extras?.getInt("id") ?: 0

        if (comentario.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show()
        }
        else{
            val comentarioRequest = DataComentario(comentario)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = RetrofitInstance.api.actualizarComentario(id, comentarioRequest)

                    if(response.success){
                        Toast.makeText(this@ClienteActivity, "Comentario registrado con éxito", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@ClienteActivity, "Comentario no registrado: ${response.data}", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@ClienteActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

}