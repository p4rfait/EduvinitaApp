package com.p4rfait.eduvinitaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarritoAdapter(
    private val lista: List<Plato>,
    private val onDeleteClick: (Plato) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgCarritoPlato)
        val nombre: TextView = view.findViewById(R.id.tvCarritoNombre)
        val precio: TextView = view.findViewById(R.id.tvCarritoPrecio)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plato = lista[position]
        holder.nombre.text = plato.nombre
        holder.precio.text = "$%.2f".format(plato.precio)
        Glide.with(holder.itemView.context).load(plato.imagenUrl).into(holder.img)
        holder.btnEliminar.setOnClickListener {
            onDeleteClick(plato)
        }
    }


    override fun getItemCount(): Int = lista.size
}
