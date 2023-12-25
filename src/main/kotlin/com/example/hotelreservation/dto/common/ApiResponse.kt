package com.example.hotelreservation.dto.common

class ApiResponse<T>(
    val message: String,
    val data: T
) {
}