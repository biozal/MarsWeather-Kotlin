package com.costoda.marsweather.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.Response
import com.costoda.marsweather.R
import com.costoda.marsweather.data.Sol
import com.costoda.marsweather.service.MarsWeatherService
import com.costoda.marsweather.views.adapters.RvAdapterTemperatureList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val adapter : RvAdapterTemperatureList

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.temperatureRecycleView)
        rv.layoutManager = LinearLayoutManager(this)
        val ws = MarsWeatherService(this)
        ws.getLatestWeather({ it: MutableList<Sol> ->
            rv.adapter = RvAdapterTemperatureList(items = it)
        }, Response.ErrorListener {
            System.out.print(it.message)
        });
    }
}