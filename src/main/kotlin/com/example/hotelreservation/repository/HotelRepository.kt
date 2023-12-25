package com.example.hotelreservation.repository

import com.example.hotelreservation.entity.Hotel
import org.springframework.data.jpa.repository.JpaRepository

interface HotelRepository : JpaRepository<Hotel, Long> {
}