package com.example.app_phonecare.phonecare.Administrador

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_phonecare.R

class ComentarioActivity : AppCompatActivity() {

    private lateinit var tvcomentario: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_comentario)

        tvcomentario = findViewById(R.id.tvComentario)

        val comentario = intent.extras?.getString("comentario").orEmpty()

        tvcomentario.text = "Comentario: $comentario"


    }
}