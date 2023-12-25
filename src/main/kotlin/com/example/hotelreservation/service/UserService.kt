package com.example.hotelreservation.service

import com.example.hotelreservation.entity.User
import com.example.hotelreservation.repository.UserRepository
import com.example.hotelreservation.security.SecurityProvider
import com.example.hotelreservation.dto.AuthTokenResponse
import com.example.hotelreservation.dto.common.ApiResponse
import com.example.hotelreservation.dto.user.SignUpRequest
import com.example.hotelreservation.dto.user.UpdateUserRequest
import com.example.hotelreservation.dto.user.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
    private val securityProvider: SecurityProvider
) {
    @Transactional
    suspend fun signUp(request: SignUpRequest) = withContext(Dispatchers.IO) {
        val isUser = userRepository.findByEmail(request.email) != null
        if (isUser) throw Error("DUPLICATE_USER")

        val userDto = User(request.email, request.name, securityProvider.hashPassword(request.password))
        val userAsync = async { userRepository.save(userDto) }
        val generateToken = securityProvider.generateToken(userAsync.await().userId!!)
        ApiResponse("SUCCESS", AuthTokenResponse(generateToken))
    }

    suspend fun signIn(email: String, password: String) : ApiResponse<AuthTokenResponse> {
        val user = userRepository.findByEmail(email) ?: throw Error("NO_USER_MATCHES_GIVEN_EMAIL")
        val hashedPassword = securityProvider.hashPassword(password)
        val isCorrectPassword = user.password == hashedPassword
        if (!isCorrectPassword) throw Error("WRONG_PASSWORD")

        val token = securityProvider.generateToken(user.userId!!)
        return ApiResponse("SUCCESS", AuthTokenResponse(token))
    }

    @Transactional
    suspend fun updateUser(userId: Long, request: UpdateUserRequest) : ApiResponse<Boolean> {
        val user = userRepository.findById(userId).getOrNull() ?: throw Error("NO_SUCH_USER")
        user.updateUser(request)
        return ApiResponse("SUCCESS", true)
    }


    @Transactional
    suspend fun deleteUser(userId: Long) : ApiResponse<Boolean> {
        val user = userRepository.findById(userId).getOrNull() ?: throw Error("NO_SUCH_USER")
        userRepository.deleteById(userId)
        return ApiResponse("SUCCESS", true)
    }



}