package com.example.finalss_gargarmiranda

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReserveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reserve)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /* val username = intent.getStringExtra("USER_NAME") ?: "Unknown"

        val btnConfirm = findViewById<Button>(R.id.btn_confirm_reservation)

        btnConfirm.setOnClickListener {

            val facility = "Computer Lab 1"   // from spinner / dropdown
            val date = "2026-01-26"           // from DatePicker
            val time = "10:00 - 11:00"        // from TimePicker

            ReservationRepo.addReservation(
                facility = facility,
                date = date,
                time = time,
                reservedBy = username
            )

            Toast.makeText(this, "Reservation saved!", Toast.LENGTH_SHORT).show()
            finish()
        } */
    }
}