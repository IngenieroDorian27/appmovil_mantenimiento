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
import com.example.app_phonecare.phonecare.Api.DataMant
import com.example.app_phonecare.phonecare.Api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IngresarMantActivity : AppCompatActivity() {

    private lateinit var etReq: EditText
    private lateinit var etAccesorios: EditText
    private lateinit var etEstado: EditText
    private lateinit var etPago: EditText
    private lateinit var btnMant: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ingresar_mant)

        etReq = findViewById(R.id.etReq)
        etAccesorios = findViewById(R.id.etAccesorios)
        etEstado = findViewById(R.id.etEstado)
        etPago = findViewById(R.id.etPago)
        btnMant = findViewById(R.id.btnMant)

        val req = intent.extras?.getString("req").orEmpty()
        val accesorios = intent.extras?.getString("accesorios").orEmpty()
        val estado = intent.extras?.getString("estado").orEmpty()
        val pago = intent.extras?.getString("pago").orEmpty()

        etReq.setText(req)
        etAccesorios.setText(accesorios)
        etEstado.setText(estado)
        etPago.setText(pago)

        btnMant.setOnClickListener {
            registrarMant()
        }

    }

    private fun registrarMant() {
        val id : Int = intent.extras?.getInt("id") ?: 0

        val req = etReq.text.toString()
        val accesorios = etAccesorios.text.toString()
        val estado = etEstado.text.toString()
        val pago = etPago.text.toString()

        if (req.isBlank() || accesorios.isBlank() || estado.isBlank() ||
            pago.isBlank()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
        }else {
            val mantRequest = DataMant(req, accesorios, estado, pago)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = RetrofitInstance.api.actualizarMant(id, mantRequest)
                    if(response.success){
                        Toast.makeText(this@IngresarMantActivity, "Mantenimiento registrado con éxito", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@IngresarMantActivity, MenuActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@IngresarMantActivity, "Error al ingresar mantenimiento: ${response.data}", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@IngresarMantActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}