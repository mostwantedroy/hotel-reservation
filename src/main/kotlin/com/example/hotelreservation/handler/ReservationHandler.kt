package com.example.hotelreservation.handler

import com.example.hotelreservation.dto.common.ApiResponse
import com.example.hotelreservation.dto.reservation.CreateReservationRequest
import com.example.hotelreservation.security.SecurityProvider
import com.example.hotelreservation.service.ReservationService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ReservationHandler(
    private val reservationService: ReservationService,
    private val securityProvider: SecurityProvider,
) {

    suspend fun createReservation(request: ServerRequest) : ServerResponse {
        return try {
            val requestBody = request.awaitBody<CreateReservationRequest>()

            val userId = securityProvider.parseUserId(securityProvider.getAuthToken(request))
            val response = reservationService.reserveRoom(userId, requestBody)

            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        } catch (e: Error) {
            val response = ApiResponse(e.toString(), false)
            ServerResponse.status(500).contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        }
    }
}