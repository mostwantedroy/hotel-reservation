package com.example.hotelreservation.dto.user

import com.example.hotelreservation.entity.User

data class UpdateUserRequest(
    val name: String?,
    val email: String?,
    val password: String?,
    val phoneNumber: String?,
) {
}