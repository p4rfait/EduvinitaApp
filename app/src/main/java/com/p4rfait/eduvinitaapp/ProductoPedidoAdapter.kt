package com.p4rfait.eduvinitaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoPedidoAdapter(private var productos: List<ProductoPedido> = emptyList()) :
    RecyclerView.Adapter<ProductoPedidoAdapter.ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_pedido, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = productos.size

    fun updateProductos(nuevosProductos: List<ProductoPedido>) {
        this.productos = nuevosProductos
        notifyDataSetChanged()
    }

    fun getTotal(): Double {
        return productos.sumOf { it.precio * it.cantidad }
    }

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombreProducto)
        private val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecioProducto)

        fun bind(producto: ProductoPedido) {
            tvNombre.text = producto.nombre
            tvPrecio.text = "$${producto.precio}"
        }
    }
}

// Data class para productos del pedido
data class ProductoPedido(
    val id: String = "",
    val nombre: String = "",
    val precio: Double = 0.0,
    val cantidad: Int = 1
)