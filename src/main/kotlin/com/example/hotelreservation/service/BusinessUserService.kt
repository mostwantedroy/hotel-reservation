package com.example.hotelreservation.service

import com.example.hotelreservation.dto.AuthTokenResponse
import com.example.hotelreservation.dto.user.SignUpRequest
import com.example.hotelreservation.entity.BusinessUser
import com.example.hotelreservation.repository.BusinessUserRepository
import com.example.hotelreservation.security.SecurityProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BusinessUserService(
    private val businessUserRepository: BusinessUserRepository,
    private val securityProvider: SecurityProvider
) {
    @Transactional
    suspend fun signUp(request: SignUpRequest) = withContext(Dispatchers.IO) {
        val isUser = businessUserRepository.findByEmail(request.email) != null
        if (isUser) throw Error("DUPLICATE_USER")

        val businessUserDto = BusinessUser(request.email, request.name, securityProvider.hashPassword(request.password))
        val businessUserAsync = async { businessUserRepository.save(businessUserDto) }
        val generateToken = securityProvider.generateToken(businessUserAsync.await().businessUserId!!)
        AuthTokenResponse(generateToken)
    }

    suspend fun signIn(email: String, password: String) : AuthTokenResponse {
        val businessUser = businessUserRepository.findByEmail(email) ?: throw Error("NO_USER_MATCHES_GIVEN_EMAIL")
        val hashedPassword = securityProvider.hashPassword(password)
        val isCorrectPassword = businessUser.password == hashedPassword
        if (!isCorrectPassword) throw Error("WRONG_PASSWORD")

        val token = securityProvider.generateToken(businessUser.businessUserId!!)
        return AuthTokenResponse(token)
    }





}