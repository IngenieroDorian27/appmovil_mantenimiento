package com.example.app_phonecare.phonecare.Api

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_phonecare.R
import com.example.app_phonecare.phonecare.Administrador.ActualizarClienteActivity
import com.example.app_phonecare.phonecare.Administrador.ComentarioActivity
import com.example.app_phonecare.phonecare.Administrador.IngresarMantActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuariosAdapter(
    private var usuarios: List<DataUsuarios>,

    ): RecyclerView.Adapter<UsuariosAdapter.UsuariosViewHolder>() {

    class UsuariosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloNombre: TextView = itemView.findViewById(R.id.tvTituloNombre)
        val tituloMarca: TextView = itemView.findViewById(R.id.tvTituloMarca)
        val updateButton: View = itemView.findViewById(R.id.updateButton)
        val deleteButton: View = itemView.findViewById(R.id.deleteButton)
        val mantButton : View = itemView.findViewById(R.id.mantButton)
        val comentarioButton : View = itemView.findViewById(R.id.comentarioButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mantenimiento_item, parent, false)
        return UsuariosViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.tituloNombre.text = usuario.NOMBRE ?: "Sin nombre"
        holder.tituloMarca.text = usuario.MARCA_TELEFONO ?: "Sin marca"

        val id = usuario.ID_USER
        val user = usuario.USUARIO
        val pasword = usuario.PASWORD
        val cedula = usuario.CEDULA
        val nombre = usuario.NOMBRE
        val correo = usuario.CORREO
        val whatsapp = usuario.WHATSAPP
        val marca = usuario.MARCA_TELEFONO
        val req = usuario.REQUERIMIENTO
        val accesorios = usuario.ACCESORIOS
        val estado = usuario.ESTADO_EQUIPO
        val pago = usuario.PAGO
        val comentario = usuario.COMENTARIO

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, ActualizarClienteActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("user", user)
            intent.putExtra("pasword", pasword)
            intent.putExtra("cedula", cedula)
            intent.putExtra("nombre", nombre)
            intent.putExtra("correo", correo)
            intent.putExtra("whatsapp", whatsapp)
            intent.putExtra("marca", marca)
            holder.itemView.context.startActivity(intent)
        }

        holder.mantButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, IngresarMantActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("req", req)
            intent.putExtra("accesorios", accesorios)
            intent.putExtra("estado", estado)
            intent.putExtra("pago", pago)
            holder.itemView.context.startActivity(intent)
        }

        holder.comentarioButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, ComentarioActivity::class.java)
            intent.putExtra("comentario", comentario)
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
                val context = holder.itemView.context

                androidx.appcompat.app.AlertDialog.Builder(context)
                    .setTitle("Confirmar eliminación")
                    .setMessage("¿Estás seguro de que deseas eliminar a este cliente?")
                    .setPositiveButton("Eliminar") { _, _ ->

                        val api = RetrofitInstance.api

                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val response = api.eliminarCliente(id)

                                if (response.success) {
                                    withContext(Dispatchers.Main) {
                                        usuarios = usuarios.filter { it.ID_USER != id }
                                        notifyDataSetChanged()
                                        android.widget.Toast.makeText(context, response.message, android.widget.Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    withContext(Dispatchers.Main) {
                                        android.widget.Toast.makeText(context, "No se pudo eliminar el cliente.", android.widget.Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } catch (e: Exception) {
                                withContext(Dispatchers.Main) {
                                    android.widget.Toast.makeText(context, "Error: ${e.message}", android.widget.Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

}