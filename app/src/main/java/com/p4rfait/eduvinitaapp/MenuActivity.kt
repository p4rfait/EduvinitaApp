package com.p4rfait.eduvinitaapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.p4rfait.eduvinitaapp.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        setDeliveryMode(true)
    }

    private fun setupClickListeners() {

        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "Buscar producto...", Toast.LENGTH_SHORT).show()
        }

        binding.btnProfile.setOnClickListener {
            Toast.makeText(this, "Ir al perfil", Toast.LENGTH_SHORT).show()

        }

        binding.btnDomicilio.setOnClickListener {
            setDeliveryMode(true)
            startActivity(Intent(this, DomicilioActivity::class.java))
        }

        binding.btnRecoger.setOnClickListener {
            setDeliveryMode(false)
            startActivity(Intent(this, RecogerTiendaActivity::class.java))
        }

        binding.navMenu.setOnClickListener {
            Toast.makeText(this, "Ya estás en Menú", Toast.LENGTH_SHORT).show()
        }

        binding.navFavoritos.setOnClickListener {
            Toast.makeText(this, "Abriendo favoritos...", Toast.LENGTH_SHORT).show()
        }

        binding.navPerfil.setOnClickListener {
            Toast.makeText(this, "Abriendo perfil...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDeliveryMode(isDomicilio: Boolean) {
        if (isDomicilio) {
            binding.btnDomicilio.setBackgroundColor(Color.parseColor("#456D66"))
            binding.btnRecoger.setBackgroundColor(Color.parseColor("#D9C2A7"))
        } else {
            binding.btnDomicilio.setBackgroundColor(Color.parseColor("#D9C2A7"))
            binding.btnRecoger.setBackgroundColor(Color.parseColor("#456D66"))
        }
    }
}