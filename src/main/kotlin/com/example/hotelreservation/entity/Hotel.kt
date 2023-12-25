package com.example.hotelreservation.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import jakarta.persistence.*

@Entity
class Hotel(
    name: String,
    businessUser: BusinessUser,
): BaseTimeEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var hotelId: Long? = null

    var name: String = name
        protected set

    var address: String? = null
        protected set

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "hotel")
    protected val mutableRooms: MutableList<Room> = mutableListOf()
    val rooms: List<Room> get() = mutableRooms.toList()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "businessUserId")
    var businessUser: BusinessUser = businessUser
        protected set

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(
            Hotel::hotelId,
            Hotel::name,
            Hotel::address
        )
        private val toStringProperties = arrayOf(
            Hotel::hotelId,
            Hotel::name,
            Hotel::address
        )
    }

}