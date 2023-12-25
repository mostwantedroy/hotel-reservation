package com.example.hotelreservation.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.example.hotelreservation.dto.user.UpdateUserRequest
import com.example.hotelreservation.dto.user.UserDto
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    email: String,
    name: String,
    password: String
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    protected val mutableReservations: MutableList<Reservation> = mutableListOf()
    val reservation: List<Reservation> get() = mutableReservations.toList()

    @Column(unique = true)
    var email: String = email
        protected set

    var name: String = name
        protected set

    var phoneNumber: String? = null
        protected set

    var password: String = password
        protected set

    override fun toString() = kotlinToString(properties = toStringProperties)

    override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

    override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(User::userId, User::email)
        private val toStringProperties = arrayOf(
            User::userId,
            User::email,
            User::name,
        )
    }

    fun updateUser(request: UpdateUserRequest) {
        this.email = request.email ?: this.email
        this.name = request.name ?: this.name
        this.password = request.password ?: this.password
        this.phoneNumber = request.phoneNumber ?: this.phoneNumber
    }


}