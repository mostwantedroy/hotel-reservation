package com.example.hotelreservation.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import jakarta.persistence.*

@Entity
class Room(
    floor: Int,
    unit: Int,
    hotel: Hotel
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var roomId: Long? = null

    var floor: Int = floor
        protected set

    var unit: Int = unit
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId")
    var hotel: Hotel = hotel
        protected set

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    protected val mutableReservations: MutableList<Reservation> = mutableListOf()
    val reservations: List<Reservation> get() = mutableReservations.toList()

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(
            Room::roomId,
            Room::floor,
            Room::unit,
            Room::hotel,
        )
        private val toStringProperties = arrayOf(
            Room::roomId,
            Room::floor,
            Room::unit,
            Room::hotel,
        )
    }







}