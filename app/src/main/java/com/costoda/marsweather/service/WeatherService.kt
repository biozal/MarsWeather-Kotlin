package com.costoda.marsweather.service

import com.android.volley.Response

interface WeatherService<T> {
    fun getLatestWeather(listener: (items: MutableList<T>) -> Unit,
                         errorListener: Response.ErrorListener)
}