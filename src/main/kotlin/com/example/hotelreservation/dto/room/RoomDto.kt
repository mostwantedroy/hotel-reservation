package com.example.hotelreservation.dto.room

import com.example.hotelreservation.dto.user.UpdateUserRequest
import com.example.hotelreservation.dto.user.UserDto
import com.example.hotelreservation.entity.Hotel
import com.example.hotelreservation.entity.Room

data class RoomDto(
    val hotelName: String,
    val floor: Int,
    val unit: Int,
) {
    companion object Mapper {
        fun from(room: Room): RoomDto {
            return RoomDto(
                hotelName = room.hotel.name,
                floor = room.floor,
                unit = room.unit,
            )
        }
    }
}