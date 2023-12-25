package com.example.hotelreservation.handler

import com.example.hotelreservation.dto.common.ApiResponse
import com.example.hotelreservation.dto.room.GetAvailableRoomsRequest
import com.example.hotelreservation.service.RoomService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class RoomHandler(
    private val roomService: RoomService,
) {
    suspend fun getAvailableRooms(request: ServerRequest) : ServerResponse {
        return try {
            val hotelId = request.pathVariable("id").toLong()
            val requestBody = request.awaitBody<GetAvailableRoomsRequest>()
            val response = roomService.getAvailableRooms(hotelId, requestBody)
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        } catch (e: Error) {
            val response = ApiResponse(e.toString(), null)
            ServerResponse.status(500).contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        }
    }

}