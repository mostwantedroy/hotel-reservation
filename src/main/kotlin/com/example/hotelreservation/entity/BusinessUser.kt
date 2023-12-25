package com.example.hotelreservation.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import jakarta.persistence.*

@Entity
class BusinessUser(
    email: String,
    name: String,
    password: String,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var businessUserId: Long? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "businessUser")
    protected var mutableHotels: MutableList<Hotel> = mutableListOf()
    val hotel: List<Hotel> get() = mutableHotels.toList()

    @Column(unique = true)
    var email: String = email
        protected set

    var name: String = name
        protected set

    var password: String = password
        protected set

    var phoneNumber: String? = null
        protected set

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(BusinessUser::businessUserId, BusinessUser::email)
        private val toStringProperties = arrayOf(
            BusinessUser::businessUserId,
            BusinessUser::email,
            BusinessUser::name,
        )
    }



}