package com.example.app_phonecare.phonecare.Administrador

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_phonecare.R
import com.example.app_phonecare.phonecare.Api.DataUsuarios
import com.example.app_phonecare.phonecare.Api.RetrofitInstance
import com.example.app_phonecare.phonecare.Api.UsuariosAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VisualizarActivity : AppCompatActivity() {

    private lateinit var rvListadoClientes: RecyclerView
    private lateinit var usuariosAdapter: UsuariosAdapter
    private var usuariosList = mutableListOf<DataUsuarios>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_visualizar)

        rvListadoClientes = findViewById(R.id.rvListadoClientes)
        rvListadoClientes.layoutManager = LinearLayoutManager(this)
        rvListadoClientes.setHasFixedSize(true)
        usuariosList = mutableListOf()
        usuariosAdapter = UsuariosAdapter(usuariosList)

        rvListadoClientes.adapter = usuariosAdapter


        obtenerUsuarios()

    }

    private fun obtenerUsuarios() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getUsuarios()

                if (response.success) {
                    withContext(Dispatchers.Main) {
                        usuariosList.clear()
                        usuariosList.addAll(response.data)
                        usuariosAdapter.notifyDataSetChanged()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@VisualizarActivity, "Error: ${response.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@VisualizarActivity, "Error de red: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}