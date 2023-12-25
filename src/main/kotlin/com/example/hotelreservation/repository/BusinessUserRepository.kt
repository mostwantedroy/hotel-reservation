package com.example.hotelreservation.repository

import com.example.hotelreservation.entity.BusinessUser
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessUserRepository : JpaRepository<BusinessUser, Long> {
    fun findByEmail(email: String): BusinessUser?
}