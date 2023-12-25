package com.example.hotelreservation.repository

import com.example.hotelreservation.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface ReservationRepository : JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.reservationId IN (:roomIds) and (r.checkOutDateTime >= :startDateTime or r.checkInDateTime <= :endDateTime)")
    fun findByIdsBetweenDateTimes(
        @Param("roomIds") ids: List<Long>,
        @Param("startDateTime") startDateTime: LocalDateTime,
        @Param("endDateTime") endDateTime: LocalDateTime
    ): List<Reservation>

    @Query("select r from Reservation r where r.room.roomId = :roomId and (r.checkOutDateTime >= :startDateTime or r.checkInDateTime <= :endDateTime)")
    fun findByIdBetweenDateTimes(
        @Param("roomId") roomId: List<Long>,
        @Param("startDateTime") startDateTime: LocalDateTime,
        @Param("endDateTime") endDateTime: LocalDateTime
    ): Reservation

}