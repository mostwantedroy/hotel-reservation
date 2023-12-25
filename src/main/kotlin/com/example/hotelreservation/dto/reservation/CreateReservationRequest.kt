package com.example.hotelreservation.dto.reservation

import java.time.LocalDateTime

class CreateReservationRequest(
    val roomId: Long,
    val checkInDateTime: LocalDateTime,
    val checkOutDateTime: LocalDateTime,
) {

}