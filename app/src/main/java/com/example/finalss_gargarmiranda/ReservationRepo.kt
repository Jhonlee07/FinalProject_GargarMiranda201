package com.example.finalss_gargarmiranda

import com.google.firebase.firestore.FirebaseFirestore

object ReservationRepo {

    private val db = FirebaseFirestore.getInstance()

    fun addReservation(
        facility: String,
        date: String,
        time: String,
        reservedBy: String
    ) {
        val reservation = hashMapOf(
            "facility" to facility,
            "date" to date,
            "time" to time,
            "reservedBy" to reservedBy,
            "status" to "reserved"
        )

        db.collection("reservations")
            .add(reservation)
    }
}