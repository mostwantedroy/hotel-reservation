package com.example.hotelreservation.dto.room

import java.time.LocalDateTime

data class GetAvailableRoomsRequest(
    val chechInDateTime: LocalDateTime,
    val chechOutDateTime: LocalDateTime,
) {
}