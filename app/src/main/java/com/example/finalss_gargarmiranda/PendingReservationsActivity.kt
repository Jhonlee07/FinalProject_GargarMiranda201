package com.example.finalss_gargarmiranda

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalss_gargarmiranda.PendingReservationsAdapter
import com.example.finalss_gargarmiranda.Reservation
import com.google.firebase.firestore.FirebaseFirestore

class PendingReservationsActivity : AppCompatActivity(), PendingReservationsAdapter.OnReservationUpdateListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PendingReservationsAdapter
    private val reservations = mutableListOf<Reservation>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_reservations)

        recyclerView = findViewById(R.id.pendingReservationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PendingReservationsAdapter(reservations, this)
        recyclerView.adapter = adapter

        fetchPendingReservations()
    }

    private fun fetchPendingReservations() {
        db.collection("reservations")
            .whereEqualTo("status", "pending")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("PendingReservations", "Listen failed.", e)
                    return@addSnapshotListener
                }

                reservations.clear()
                for (doc in snapshots!!) {

                    val submittedBy = doc.get("submittedBy") as? Map<*, *>
                    val reservationSlot = doc.get("reservationSlot") as? Map<*, *>

                    val reservation = Reservation(
                        id = doc.id,
                        facility = doc.getString("facilityName") ?: "",
                        date = reservationSlot?.get("date")?.toString() ?: "",
                        time = reservationSlot?.get("time")?.toString() ?: "",
                        reservedBy = submittedBy?.get("name")?.toString() ?: "",
                        status = doc.getString("status") ?: ""
                    )

                    Log.d("PendingReservations", "Parsed reservation: $reservation")
                    reservations.add(reservation)
                }

                adapter.notifyDataSetChanged()
            }
    }

    override fun onApprove(reservation: Reservation) {
        updateReservationStatus(reservation, "approved")
    }

    override fun onDeny(reservation: Reservation) {
        updateReservationStatus(reservation, "denied")
    }

    private fun updateReservationStatus(reservation: Reservation, status: String) {
        db.collection("reservations").document(reservation.id)
            .update("status", status)
            .addOnSuccessListener {
                Log.d("PendingReservations", "Reservation status updated to $status")
                adapter.removeReservation(reservation)
            }
            .addOnFailureListener { e ->
                Log.w("PendingReservations", "Error updating reservation status", e)
            }
    }
}
