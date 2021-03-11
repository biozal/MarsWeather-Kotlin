package com.costoda.marsweather.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.costoda.marsweather.data.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MarsPhotoService(var context: Context)
{
    private var requestQueue: RequestQueue? = null

    init {
        requestQueue = Volley.newRequestQueue(context)
    }

    
}