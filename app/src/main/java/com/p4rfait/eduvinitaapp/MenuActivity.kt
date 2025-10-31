package com.p4rfait.eduvinitaapp

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


        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "Buscar producto...", Toast.LENGTH_SHORT).show()
        }

        binding.btnProfile.setOnClickListener {
            Toast.makeText(this, "Ir al perfil", Toast.LENGTH_SHORT).show()
        }


        binding.navMenu.setOnClickListener {
            Toast.makeText(this, "Ya estás en Menú", Toast.LENGTH_SHORT).show()
        }

        binding.navFavoritos.setOnClickListener {
            Toast.makeText(this, "Abrir favoritos", Toast.LENGTH_SHORT).show()
        }

        binding.navPerfil.setOnClickListener {
            Toast.makeText(this, "Abrir perfil", Toast.LENGTH_SHORT).show()
        }
    }
}