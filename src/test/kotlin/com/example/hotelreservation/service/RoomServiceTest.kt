package com.example.hotelreservation.service

import com.example.hotelreservation.entity.BusinessUser
import com.example.hotelreservation.entity.Hotel
import com.example.hotelreservation.entity.Room
import com.example.hotelreservation.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RoomServiceTest @Autowired constructor(
    val roomService: RoomService,
    val RoomRepository: RoomRepository,
) {
    @Test
    fun test() {






    }



}