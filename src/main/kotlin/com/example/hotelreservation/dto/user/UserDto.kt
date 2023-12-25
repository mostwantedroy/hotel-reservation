package com.example.hotelreservation.dto.user

import com.example.hotelreservation.entity.User

data class UserDto(
    var email: String?,
    var name: String?,
    var password: String?,
    val phoneNumber: String?,
) {
    companion object Mapper {
        fun fromUpdateUserRequest(updateUserRequest: UpdateUserRequest): UserDto {
            return UserDto(
                email = updateUserRequest.email,
                name = updateUserRequest.name,
                password = updateUserRequest.password,
                phoneNumber = updateUserRequest.phoneNumber,
            )
        }
    }
}