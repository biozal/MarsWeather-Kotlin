package com.costoda.marsweather.data

data class RoverPhoto (
    val id: Long,
    val sol: Long,
    val camera: RoverCamera,
    val imgSrc: String,
    val earthDate: String,
    val rover: Rover
)
