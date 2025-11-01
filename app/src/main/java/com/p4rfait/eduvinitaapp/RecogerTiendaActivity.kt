package com.p4rfait.eduvinitaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val db = FirebaseFirestore.getInstance()
                val entrega = hashMapOf(
                    "metodoEntrega" to "Recoger en tienda",
                    "direccion" to "Local principal Eduvinita Café"
                )

                db.collection("usuarios")
                    .document(user.uid)
                    .set(entrega, SetOptions.merge())
                    .addOnSuccessListener {
                        Toast.makeText(this, "Método de entrega guardado: Recoger en tienda", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al guardar método de entrega", Toast.LENGTH_SHORT).show()
                    }
            }
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

        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
            finish()
        }
    }

    private fun goToDomicilio() {
        startActivity(Intent(this, DomicilioActivity::class.java))
        finish()
    }
}