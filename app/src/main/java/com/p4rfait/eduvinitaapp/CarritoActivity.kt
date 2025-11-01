package com.p4rfait.eduvinitaapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CarritoActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: CarritoAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var tvTotal: TextView

    private var listaCarrito = mutableListOf<Plato>()
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        recycler = findViewById(R.id.recyclerCarrito)
        tvTotal = findViewById(R.id.tvTotal)
        val btnComprar = findViewById<Button>(R.id.btnComprar)

        adapter = CarritoAdapter(listaCarrito) { plato ->
            eliminarItem(plato)
        }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        cargarCarrito()

        btnComprar.setOnClickListener {
            realizarCompra()
        }
    }

    private fun cargarCarrito() {
        val user = auth.currentUser ?: return

        db.collection("carritos")
            .document(user.uid)
            .collection("items")
            .get()
            .addOnSuccessListener { result ->
                listaCarrito.clear()
                total = 0.0
                for (doc in result) {
                    val plato = Plato(
                        id = doc.id,
                        nombre = doc.getString("nombre") ?: "",
                        imagenUrl = doc.getString("imagenUrl") ?: "",
                        descripcion = "",
                        precio = doc.getDouble("precio") ?: 0.0
                    )
                    listaCarrito.add(plato)
                    total += plato.precio
                }
                tvTotal.text = "Total: $%.2f".format(total)
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al cargar el carrito", Toast.LENGTH_SHORT).show()
            }
    }

    private fun eliminarItem(plato: Plato) {
        val user = auth.currentUser ?: return
        db.collection("carritos")
            .document(user.uid)
            .collection("items")
            .document(plato.id)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "${plato.nombre} eliminado", Toast.LENGTH_SHORT).show()
                cargarCarrito()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
            }
    }

    private fun realizarCompra() {
        val user = auth.currentUser ?: return

        if (listaCarrito.isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            return
        }

        val pedido = hashMapOf(
            "userId" to user.uid,
            "fecha" to System.currentTimeMillis(),
            "total" to total,
            "items" to listaCarrito.map {
                mapOf(
                    "nombre" to it.nombre,
                    "precio" to it.precio,
                    "imagenUrl" to it.imagenUrl
                )
            }
        )

        db.collection("pedidos")
            .add(pedido)
            .addOnSuccessListener {
                // Vaciar carrito
                limpiarCarrito(user.uid)
                Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al realizar la compra", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limpiarCarrito(userId: String) {
        db.collection("carritos").document(userId).collection("items")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    doc.reference.delete()
                }
            }
    }
}
