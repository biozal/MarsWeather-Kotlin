package com.costoda.marsweather.data

import java.util.*

data class Sol(
    val name: String,
    val at: SolData,
    val hws: SolData,
    val pre: SolData,
    val season: String,
    val firstDate: Date,
    val lastDate: Date)