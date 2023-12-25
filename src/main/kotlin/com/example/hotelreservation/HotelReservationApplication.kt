package com.example.hotelreservation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class HotelReservationApplication

fun main(args: Array<String>) {
    runApplication<HotelReservationApplication>(*args)
}
