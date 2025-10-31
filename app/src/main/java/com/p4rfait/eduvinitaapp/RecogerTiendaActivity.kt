package com.p4rfait.eduvinitaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.p4rfait.eduvinitaapp.databinding.ActivityRecogerTiendaBinding

class RecogerTiendaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecogerTiendaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecogerTiendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()

        binding.tvDireccionTienda.text = "2da Calle Oriente y 3ra Avenida Sur\nSanta Tecla, La Libertad"
        binding.tvHorario.text = "Lunes a Domingo:\n7:00 AM - 9:00 PM"
    }

    private fun setupClickListeners() {
        binding.btnConfirmarRecoger.setOnClickListener {
            Toast.makeText(this, "Pedido programado para recoger en Eduvinita Café", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        binding.btnDomicilioInactive.setOnClickListener {
            goToDomicilio()
        }

        binding.navMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        binding.navFavoritos.setOnClickListener {
            Toast.makeText(this, "Abriendo favoritos...", Toast.LENGTH_SHORT).show()
        }

        binding.navPerfil.setOnClickListener {
            Toast.makeText(this, "Abriendo perfil...", Toast.LENGTH_SHORT).show()
        }

        // Botones de header
        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "Buscar producto...", Toast.LENGTH_SHORT).show()
        }

        binding.btnProfile.setOnClickListener {
            Toast.makeText(this, "Ir al perfil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToDomicilio() {
        startActivity(Intent(this, DomicilioActivity::class.java))
        finish()
    }
}