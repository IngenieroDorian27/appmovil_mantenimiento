package com.example.app_phonecare.phonecare.Administrador

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_phonecare.R

class MenuActivity : AppCompatActivity() {

    private lateinit var cvNuevo: CardView
    private lateinit var cvVisualizar: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)

        cvNuevo = findViewById(R.id.cvNuevoUsuario)
        cvVisualizar = findViewById(R.id.cvVisualizar)

        cvNuevo.setOnClickListener {
            val intent = Intent(this, NuevoClienteActivity::class.java)
            startActivity(intent)
        }

        cvVisualizar.setOnClickListener {
            val intent = Intent(this, VisualizarActivity::class.java)
            startActivity(intent)
        }

    }
}