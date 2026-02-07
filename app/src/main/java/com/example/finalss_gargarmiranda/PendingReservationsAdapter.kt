package com.example.finalss_gargarmiranda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PendingReservationsAdapter(
    private val reservations: MutableList<Reservation>,
    private val listener: OnReservationUpdateListener
) : RecyclerView.Adapter<PendingReservationsAdapter.ViewHolder>() {

    interface OnReservationUpdateListener {
        fun onApprove(reservation: Reservation)
        fun onDeny(reservation: Reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pending_reservation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.bind(reservation)
    }

    override fun getItemCount(): Int = reservations.size

    fun removeReservation(reservation: Reservation) {
        val position = reservations.indexOf(reservation)
        if (position != -1) {
            reservations.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val facilityTextView: TextView = itemView.findViewById(R.id.facilityTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val reservedByTextView: TextView = itemView.findViewById(R.id.reservedByTextView)
        private val approveButton: Button = itemView.findViewById(R.id.approveButton)
        private val denyButton: Button = itemView.findViewById(R.id.denyButton)

        fun bind(reservation: Reservation) {
            facilityTextView.text = reservation.facility
            dateTextView.text = reservation.date
            timeTextView.text = reservation.time
            reservedByTextView.text = reservation.reservedBy

            approveButton.setOnClickListener {
                listener.onApprove(reservation)
            }

            denyButton.setOnClickListener {
                listener.onDeny(reservation)
            }
        }
    }
}