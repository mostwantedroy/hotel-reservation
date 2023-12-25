package com.example.hotelreservation.handler

import com.example.hotelreservation.service.UserService
import com.example.hotelreservation.dto.AuthTokenResponse
import com.example.hotelreservation.dto.common.ApiResponse
import com.example.hotelreservation.dto.user.SignInRequest
import com.example.hotelreservation.dto.user.SignUpRequest
import com.example.hotelreservation.dto.user.UpdateUserRequest
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class UserHandler(
    private var userService: UserService
) {
    suspend fun signUp(request: ServerRequest) : ServerResponse {
        return try {
            val requestBody = request.awaitBody<SignUpRequest>()
            val response = userService.signUp(requestBody)
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        } catch (e: Error) {
            val response = ApiResponse(e.toString(), false)
            ServerResponse.status(500).contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        }
    }

    suspend fun signIn(request: ServerRequest) : ServerResponse {
        return try {
            val requestBody = request.awaitBody<SignInRequest>()
            val response : ApiResponse<AuthTokenResponse> = userService.signIn(requestBody.email, requestBody.password)
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        } catch (e: Error) {
            val response = ApiResponse(e.toString(), false)
            ServerResponse.status(500).contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        }
    }

    suspend fun updateUser(request: ServerRequest): ServerResponse {
        return try {
            val userId = request.pathVariable("id").toLong()
            val requestBody = request.awaitBody<UpdateUserRequest>()
            val response: ApiResponse<Boolean> = userService.updateUser(userId, requestBody)
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        } catch (e: Error) {
            val response = ApiResponse(e.toString(), false)
            ServerResponse.status(500).contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        }
    }

    suspend fun deleteUser(request: ServerRequest): ServerResponse {
        return try {
            val userId = request.pathVariable("id").toLong()
            val response = userService.deleteUser(userId)
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        } catch (e: Error) {
            val response = ApiResponse(e.toString(), false)
            ServerResponse.status(500).contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(response)
        }
    }






}