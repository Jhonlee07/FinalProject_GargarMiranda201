package com.example.finalss_gargarmiranda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class FillUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_up)

        val facilityName = intent.getStringExtra("FACILITY_NAME")

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val nameEditText = findViewById<EditText>(R.id.ETFillUp_Name)
        val positionEditText = findViewById<EditText>(R.id.ETFillUp_Position)
        val titleEditText = findViewById<EditText>(R.id.ETFillUp_Title)
        val datesEditText = findViewById<EditText>(R.id.ETFillUp_Dates)
        val daysEditText = findViewById<EditText>(R.id.ETFillUp_Days)
        val timeFromEditText = findViewById<EditText>(R.id.ETFillUp_TimeFrom)
        val timeToEditText = findViewById<EditText>(R.id.ETFillUp_TimeTo)
        val attendeesEditText = findViewById<EditText>(R.id.ETFillUp_Attendees)
        val speakerEditText = findViewById<EditText>(R.id.ETFillUp_Speaker)
        val submitButton = findViewById<Button>(R.id.BTN_Submit)

        submitButton.setOnClickListener {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser == null) {
                Toast.makeText(this, "You must be logged in to make a reservation.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val time = "${timeFromEditText.text} - ${timeToEditText.text}"
            val date = datesEditText.text.toString()

            if (facilityName != null) {
                ReservationRepo.isReservationExisting(facilityName, date, time) { isExisting ->
                    if (isExisting) {
                        Toast.makeText(this, "This facility is already reserved or pending at the selected date and time.", Toast.LENGTH_SHORT).show()
                    } else {
                        val reservationDetails = hashMapOf(
                            "facilityName" to facilityName,
                            "status" to "pending",
                            "submittedBy" to hashMapOf(
                                "name" to nameEditText.text.toString(),
                                "position" to positionEditText.text.toString(),
                                "email" to currentUser.email
                            ),
                            "activityDetails" to hashMapOf(
                                "title" to titleEditText.text.toString(),
                                "attendees" to attendeesEditText.text.toString(),
                                "speaker" to speakerEditText.text.toString()
                            ),
                            "reservationSlot" to hashMapOf(
                                "date" to date,
                                "days" to daysEditText.text.toString(),
                                "time" to time
                            )
                        )

                        ReservationRepo.addReservation(reservationDetails)

                        Toast.makeText(this, "Reservation submitted for approval!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Error: Facility name not found.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
