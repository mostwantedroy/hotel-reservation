package com.example.hotelreservation.repository

import com.example.hotelreservation.entity.Hotel
import com.example.hotelreservation.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface RoomRepository : JpaRepository<Room, Long> {
    @Query("select r from Room r inner join Hotel h where h.hotelId = :hotelId")
    fun findWithHotelByHotelId(@Param("hotelId") id: Long) : List<Room>

    @Query("select r from Room r where r.roomId IN (:roomIds)")
    fun findByIds(@Param("roomIds") ids: List<Long>)
}