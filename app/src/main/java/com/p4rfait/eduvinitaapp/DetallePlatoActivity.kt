package com.p4rfait.eduvinitaapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DetallePlatoActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_plato)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val id = intent.getStringExtra("id") ?: ""
        val nombre = intent.getStringExtra("nombre") ?: "Plato sin nombre"
        val descripcion = intent.getStringExtra("descripcion") ?: "Sin descripción"
        val imagenUrl = intent.getStringExtra("imagenUrl") ?: ""
        val precio = intent.getDoubleExtra("precio", 0.0)

        val imgPlato = findViewById<ImageView>(R.id.imgDetallePlato)
        val tvNombre = findViewById<TextView>(R.id.tvDetalleNombre)
        val tvDescripcion = findViewById<TextView>(R.id.tvDetalleDescripcion)
        val tvPrecio = findViewById<TextView>(R.id.tvDetallePrecio)
        val btnAgregar = findViewById<Button>(R.id.btnAgregarCarrito)

        tvNombre.text = nombre
        tvDescripcion.text = descripcion
        tvPrecio.text = "Precio: $%.2f".format(precio)
        if (imagenUrl.isNotEmpty()) {
            Glide.with(this).load(imagenUrl).into(imgPlato)
        }

        btnAgregar.setOnClickListener {
            if (id.isEmpty()) {
                Toast.makeText(this, "Error: ID del plato vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            agregarAlCarrito(id, nombre, precio, imagenUrl)
        }
    }

    private fun agregarAlCarrito(id: String, nombre: String, precio: Double, imagenUrl: String) {
        val user = auth.currentUser
        if (user == null) {
            Toast.makeText(this, "Debes iniciar sesión para agregar al carrito", Toast.LENGTH_SHORT).show()
            Log.e("CarritoError", "Usuario no autenticado")
            return
        }

        val item = hashMapOf(
            "id" to id,
            "nombre" to nombre,
            "precio" to precio,
            "imagenUrl" to imagenUrl,
            "cantidad" to 1,
        )

        val path = db.collection("carritos")
            .document(user.uid)
            .collection("items")
            .document(id)

        path.set(item)
            .addOnSuccessListener {
                Toast.makeText(this, "Agregado al carrito correctamente", Toast.LENGTH_SHORT).show()
                Log.d("Carrito", "Item agregado correctamente")
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al agregar al carrito: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("CarritoError", "Error Firestore: ", e)
            }
    }
}
