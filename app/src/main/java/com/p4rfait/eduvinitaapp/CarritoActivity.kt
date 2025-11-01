package com.p4rfait.eduvinitaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.p4rfait.eduvinitaapp.databinding.ActivityCarritoBinding

class CarritoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarritoBinding
    private lateinit var carritoAdapter: CarritoAdapter
    private var costoEnvio = 5.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
        updateResumen()
    }

    private fun setupRecyclerView() {
        carritoAdapter = CarritoAdapter()

        binding.recyclerViewCarrito.apply {
            layoutManager = LinearLayoutManager(this@CarritoActivity)
            adapter = carritoAdapter
        }

    }

    private fun setupClickListeners() {
        binding.btnPagarOrden.setOnClickListener {
            if (carritoAdapter.getItemCount() > 0) {
                val total = carritoAdapter.getSubtotal() + costoEnvio
                val intent = Intent(this, PedidoActivity::class.java).apply {
                    putExtra("TOTAL", total)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Agrega productos al carrito primero", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "Buscar producto...", Toast.LENGTH_SHORT).show()
        }

        binding.btnCart.setOnClickListener {
            Toast.makeText(this, "Ya estás en el carrito", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateResumen() {
        val subtotal = carritoAdapter.getSubtotal()
        val total = subtotal + costoEnvio

        binding.tvSubtotal.text = "$${"%.2f".format(subtotal)}"
        binding.tvEnvio.text = "$${"%.2f".format(costoEnvio)}"
        binding.tvTotal.text = "$${"%.2f".format(total)}"

        // Actualizar texto del botón con el total
        binding.btnPagarOrden.text = "Pagar Mi Orden - $${"%.2f".format(total)}"
    }

    private fun updateVistaCarrito() {
        if (carritoAdapter.getItemCount() > 0) {
            binding.tvCarritoVacio.visibility = android.view.View.GONE
            binding.recyclerViewCarrito.visibility = android.view.View.VISIBLE
        } else {
            binding.tvCarritoVacio.visibility = android.view.View.VISIBLE
            binding.recyclerViewCarrito.visibility = android.view.View.GONE
        }
        updateResumen()
    }
}

// Data class para productos del carrito
data class ProductoCarrito(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var cantidad: Int,
    val imagenRes: Int
)