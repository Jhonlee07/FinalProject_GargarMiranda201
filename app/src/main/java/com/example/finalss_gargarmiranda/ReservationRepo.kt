package com.example.finalss_gargarmiranda

import com.google.firebase.firestore.FirebaseFirestore

object ReservationRepo {

    private val db = FirebaseFirestore.getInstance()

    fun addReservation(reservation: Map<String, Any>) {
        db.collection("reservations").add(reservation)
    }

    fun isReservationExisting(
        facility: String,
        date: String,
        time: String,
        callback: (Boolean) -> Unit
    ) {
        db.collection("reservations")
            .whereEqualTo("facilityName", facility)
            .whereEqualTo("reservationSlot.date", date)
            .whereEqualTo("reservationSlot.time", time)
            .whereIn("status", listOf("reserved", "pending"))
            .get()
            .addOnSuccessListener { documents ->
                callback(!documents.isEmpty)
            }
            .addOnFailureListener {
                callback(false)
            }
    }
}