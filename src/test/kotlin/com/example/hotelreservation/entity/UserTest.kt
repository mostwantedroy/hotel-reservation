package com.example.hotelreservation.entity

import com.example.hotelreservation.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserTest (
    @Autowired
    private val userRepository: UserRepository,
) {

    @Test
    fun test() {

        val list1 = listOf(1, 2, 3, 4, 5)
        val list2 = listOf(3, 4, 5, 6, 7)

        val difference = list2.toSet().minus(list1.toSet())
        println(difference.joinToString()) // 6, 7

        val duplicationCount = list2.size - difference.size
        println(duplicationCount) // 3

//        val user = User("asdf@naver.com", "홍길동")

//        userRepository.save(user)
//        userRepository.flush()
//        println(user)
//
//        val foundUser = userRepository.findById(1L).get()
//
//        assertTrue(user == foundUser)
    }





}