package com.p4rfait.eduvinitaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.p4rfait.eduvinitaapp.databinding.ActivityPedidoBinding

class PedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPedidoBinding
    private lateinit var productoAdapter: ProductoPedidoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
        updateTotal()
    }

    private fun setupRecyclerView() {
        productoAdapter = ProductoPedidoAdapter()

        binding.recyclerViewProductos.apply {
            layoutManager = LinearLayoutManager(this@PedidoActivity)
            adapter = productoAdapter
            setHasFixedSize(true)
        }

    }

    private fun setupClickListeners() {
        // Botón Cambiar dirección (redirige a DomicilioActivity)
        binding.btnCambiarDireccion.setOnClickListener {
            startActivity(Intent(this, DomicilioActivity::class.java))
        }

        // Botón comprar
        binding.btnComprar.setOnClickListener {
            if (productoAdapter.itemCount > 0) {
                Toast.makeText(this, "¡Pedido realizado exitosamente!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Agrega productos al pedido", Toast.LENGTH_SHORT).show()
            }
        }

        // NAVEGACIÓN INFERIOR
        binding.navMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        binding.navFavoritos.setOnClickListener {
            Toast.makeText(this, "Abrir favoritos", Toast.LENGTH_SHORT).show()
        }

        binding.navPerfil.setOnClickListener {
            Toast.makeText(this, "Abrir perfil", Toast.LENGTH_SHORT).show()
        }

        // Botones de header
        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "Buscar producto...", Toast.LENGTH_SHORT).show()
        }

        binding.btnCart .setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
            finish()
        }
    }

    private fun updateTotal() {
        val total = productoAdapter.getTotal()
        binding.tvTotal.text = "$${"%.2f".format(total)}"
    }
}