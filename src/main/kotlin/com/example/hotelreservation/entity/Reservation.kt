package com.example.hotelreservation.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Reservation(
    checkInDateTime: LocalDateTime,
    checkOutDateTime: LocalDateTime,
    user: User,
    room: Room
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var reservationId: Long? = null

    var checkInDateTime: LocalDateTime = checkInDateTime
        protected set

    var checkOutDateTime: LocalDateTime = checkOutDateTime
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    var user: User = user
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    var room: Room = room
        protected set

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(
            Reservation::reservationId,
            Reservation::room,
            Reservation::user,
            Reservation::checkInDateTime,
            Reservation::checkOutDateTime,
        )
        private val toStringProperties = arrayOf(
            Reservation::reservationId,
            Reservation::room,
            Reservation::user,
            Reservation::checkInDateTime,
            Reservation::checkOutDateTime,
        )
    }








}