package com.p4rfait.eduvinitaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class CarritoAdapter : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    private var productos: List<ProductoCarrito> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = productos.size

    fun updateProductos(nuevosProductos: List<ProductoCarrito>) {
        this.productos = nuevosProductos
        notifyDataSetChanged()
    }

    fun getSubtotal(): Double {
        return productos.sumOf { it.precio * it.cantidad }
    }

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(producto: ProductoCarrito) {
            // Encuentra las vistas usando itemView.findViewById
            val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
            val tvNombreProducto: TextView = itemView.findViewById(R.id.tvNombreProducto)
            val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
            val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecio)
            val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
            val btnMenos: Button = itemView.findViewById(R.id.btnMenos)
            val btnMas: Button = itemView.findViewById(R.id.btnMas)
            val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)

            // Configurar las vistas
            imgProducto.setImageResource(producto.imagenRes)
            tvNombreProducto.text = producto.nombre
            tvDescripcion.text = producto.descripcion
            tvPrecio.text = "$${producto.precio}"
            tvCantidad.text = producto.cantidad.toString()

            btnMenos.setOnClickListener {
                if (producto.cantidad > 1) {
                    producto.cantidad--
                    tvCantidad.text = producto.cantidad.toString()
                }
            }

            btnMas.setOnClickListener {
                producto.cantidad++
                tvCantidad.text = producto.cantidad.toString()
            }

            btnEliminar.setOnClickListener {
                // Lógica para eliminar
            }
        }
    }
}