package com.example.finalss_gargarmiranda

import android.content.Context
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

object NavigationHelper {

    fun setupBottomNavigation(context: Context, bottomNavigationView: BottomNavigationView, currentItemId: Int) {
        bottomNavigationView.selectedItemId = currentItemId
        
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_notifications -> {
                    if (currentItemId != R.id.navigation_notifications) {
                        val intent = Intent(context, NotificationActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        context.startActivity(intent)
                    }
                    true
                }
                R.id.navigation_home -> {
                    if (currentItemId != R.id.navigation_home) {
                        val intent = Intent(context, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        context.startActivity(intent)
                    }
                    true
                }
                R.id.navigation_settings -> {
                    if (currentItemId != R.id.navigation_settings) {
                        val intent = Intent(context, SettingsActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        context.startActivity(intent)
                    }
                    true
                }
                else -> false
            }
        }
    }
}