package com.p4rfait.eduvinitaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        tv.text = "Bienvenido a Eduvinita 🍤"
        setContentView(tv)
    }
}