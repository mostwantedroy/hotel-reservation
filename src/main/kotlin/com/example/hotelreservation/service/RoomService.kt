package com.example.hotelreservation.service

import com.example.hotelreservation.dto.common.ApiResponse
import com.example.hotelreservation.dto.room.GetAvailableRoomsRequest
import com.example.hotelreservation.dto.room.RoomDto
import com.example.hotelreservation.entity.Room
import com.example.hotelreservation.repository.HotelRepository
import com.example.hotelreservation.repository.ReservationRepository
import com.example.hotelreservation.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RoomService(
    private val roomRepository: RoomRepository,
    private val reservationRepository: ReservationRepository,
    private val hotelRepository: HotelRepository,
) {

    suspend fun getAvailableRooms(hotelId: Long, request: GetAvailableRoomsRequest): ApiResponse<List<RoomDto>> {
        val rooms = roomRepository.findWithHotelByHotelId(hotelId)
        val roomIds = rooms.map { room: Room -> room.roomId!! }

        val overlappedReservations = reservationRepository.findByIdsBetweenDateTimes(roomIds, request.chechInDateTime, request.chechOutDateTime)
        val overlappedRoomIds = overlappedReservations.map { reservation -> reservation.room.roomId }

        return ApiResponse("AVAILABLE_ROOMS", rooms.filter { it.roomId !in overlappedRoomIds }.map { it -> RoomDto.from(it) })
    }



}