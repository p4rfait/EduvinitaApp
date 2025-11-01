package com.p4rfait.eduvinitaapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlatoAdapter(private var listaPlatos: List<Plato>) :
    RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder>() {

    class PlatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenPlato: ImageView = itemView.findViewById(R.id.imgPlato)
        val nombrePlato: TextView = itemView.findViewById(R.id.tvNombrePlato)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plato_card, parent, false)
        return PlatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val plato = listaPlatos[position]
        holder.nombrePlato.text = plato.nombre
        Glide.with(holder.itemView.context)
            .load(plato.imagenUrl)
            .into(holder.imagenPlato)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetallePlatoActivity::class.java)
            intent.putExtra("id", plato.id)
            intent.putExtra("nombre", plato.nombre)
            intent.putExtra("descripcion", plato.descripcion)
            intent.putExtra("imagenUrl", plato.imagenUrl)
            intent.putExtra("precio", plato.precio)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaPlatos.size

    fun updateData(newList: List<Plato>) {
        listaPlatos = newList
        notifyDataSetChanged()
    }
}
