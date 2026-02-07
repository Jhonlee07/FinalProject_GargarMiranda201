package com.example.finalss_gargarmiranda

data class Reservation(
    @JvmField
    var id: String = "",
    @JvmField
    val facility: String = "",
    @JvmField
    val date: String = "",
    @JvmField
    val time: String = "",
    @JvmField
    val reservedBy: String = "",
    @JvmField
    val approvedBy: String = "",
    @JvmField
    val status: String = ""
)