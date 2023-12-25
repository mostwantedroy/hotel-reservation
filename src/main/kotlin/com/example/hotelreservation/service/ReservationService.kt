package com.example.hotelreservation.service

import com.example.hotelreservation.dto.common.ApiResponse
import com.example.hotelreservation.dto.reservation.CreateReservationRequest
import com.example.hotelreservation.entity.Reservation
import com.example.hotelreservation.entity.Room
import com.example.hotelreservation.repository.ReservationRepository
import com.example.hotelreservation.repository.RoomRepository
import com.example.hotelreservation.repository.UserRepository
import com.example.hotelreservation.security.SecurityProvider
import kotlinx.coroutines.Dispatchers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional(readOnly = true)
class ReservationService(
    private val securityProvider: SecurityProvider,
    private val reservationRepository: ReservationRepository,
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository,
) {
    @Transactional
    suspend fun reserveRoom(userId: Long, request: CreateReservationRequest) = withContext(Dispatchers.IO) {
        val room = async {
            roomRepository.findById(request.roomId).getOrNull()
        }.await() ?: throw Error("NO_SUCH_ROOM")

        val user = async {
            userRepository.findById(userId).getOrNull()
        }.await() ?: throw Error("NO_SUCH_USER")

        reservationRepository.save(Reservation(request.checkInDateTime, request.checkOutDateTime, user, room))
        ApiResponse("RESERVATION_COMPLETE", true)
    }

}