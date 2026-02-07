package com.example.finalss_gargarmiranda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("USER_NAME")
        val tvUsernameDisplay = findViewById<TextView>(R.id.tv_username_display)

        if (!username.isNullOrEmpty()) {
            tvUsernameDisplay.text = username
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationHelper.setupBottomNavigation(this, bottomNav, R.id.navigation_home)

        val btnReserve: ImageButton = findViewById(R.id.btn_reserve)
        btnReserve.setOnClickListener {
            val intent = Intent(this, ReserveActivity::class.java)
            startActivity(intent)
        }

        val btnList: ImageButton = findViewById(R.id.btn_reservation_list)
        btnList.setOnClickListener {
            val intent = Intent(this, ReservationListActivity::class.java)
            startActivity(intent)
        }

        val btnReport: ImageButton = findViewById(R.id.btn_report_facility)
        btnReport.setOnClickListener {
            val intent = Intent(this, ReportFacilityActivity::class.java)
            startActivity(intent)
        }
    }
}