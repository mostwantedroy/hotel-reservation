package com.example.hotelreservation.router

import com.example.hotelreservation.handler.ReservationHandler
import com.example.hotelreservation.handler.RoomHandler
import com.example.hotelreservation.handler.UserHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {
    @Autowired
    private lateinit var userHandler: UserHandler
    @Autowired
    private lateinit var reservationHandler: ReservationHandler
    @Autowired
    private lateinit var roomHandler: RoomHandler

    @Bean
    fun apiRouter(): RouterFunction<ServerResponse> {
        return coRouter {
            accept(APPLICATION_JSON).nest {
                "/api".nest {
                    "/users".nest {
                        PUT("/{id}") { userHandler.updateUser(it) }
                        DELETE("/{id}") { userHandler.deleteUser(it) }
                    }
                    "/auth".nest {
                        POST("/sign-in") { userHandler.signIn(it) }
                        POST("/sign-up") { userHandler.signUp(it) }
                    }
                    "/reservations".nest {
                        POST("") { reservationHandler.createReservation(it) }
                    }
                    "/hotels".nest {
                        "/{id}".nest {
                            "/rooms".nest {
                                GET("/available") { roomHandler.getAvailableRooms(it) }
                            }
                        }
                    }
                    "/rooms".nest {
                        GET("/available")
                    }
                }
            }
        }

    }






}