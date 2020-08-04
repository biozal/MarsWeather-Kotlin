package com.costoda.marsweather.data

data class SolData(
    val dataType : SolDataType, //AT, HWS, PRE
    val averageSample: Double,  //av
    val totalSamples: Int,   //ct
    val minimumData: Double,    //mn
    val maximumData: Double,    //mx
    val unit: String            //temp in Fahrenheit
)

enum class SolDataType {
    ATMOSPHERICTEMPERATURE,
    HORIZONTALWINDSPEED,
    ATMOSPHERICPRESSURE
}
