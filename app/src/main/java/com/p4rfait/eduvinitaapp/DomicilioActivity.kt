package com.p4rfait.eduvinitaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.p4rfait.eduvinitaapp.databinding.ActivityDomicilioBinding

class DomicilioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDomicilioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDomicilioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {

        binding.btnConfirmarDireccion.setOnClickListener {
            val calle = binding.etCalle.text.toString()
            val numero = binding.etNumero.text.toString()
            val colonia = binding.etColonia.text.toString()
            val ciudad = binding.etCiudad.text.toString()

            if (calle.isEmpty() || numero.isEmpty() || colonia.isEmpty() || ciudad.isEmpty()) {
                Toast.makeText(this, "Completa los campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Dirección guardada exitosamente", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        binding.btnRecogerInactive.setOnClickListener {
            goToRecogerTienda()
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

        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "Buscar producto...", Toast.LENGTH_SHORT).show()
        }

        binding.btnProfile.setOnClickListener {
            Toast.makeText(this, "Ir al perfil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToRecogerTienda() {
        startActivity(Intent(this, RecogerTiendaActivity::class.java))
        finish()
    }
}