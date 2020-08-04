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

class MarsWeatherService(var context: Context)
    : WeatherService<Sol> {
    private var requestQueue : RequestQueue? = null

    init {
        requestQueue = Volley.newRequestQueue(context)
    }

    override fun getLatestWeather(
        listener: (items: MutableList<Sol>) -> Unit,
        errorListener: Response.ErrorListener) {
        val url = "https://api.nasa.gov/insight_weather/?api_key=vbQYttWflaZWIAYHdiFx0xGBAKt2QgUa3mpCTBCv&feedtype=json&ver=1.0"
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
            //set the timezone parser to UTC
            val results = mutableListOf<Sol>()
            val keys = it.getJSONArray(ROOTKEY)
            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            if (keys.length() > 0){
                for(counter in 0 until keys.length()) {
                    val item = keys.getString(counter)
                    if (item != null) {
                        //parse data from the results
                        val solJson = it.getJSONObject(item)
                        val atJson = solJson.getJSONObject(ATMOSPHERICTEMPKEY)
                        val hwsJson  = solJson.getJSONObject(HORIZONTALWINDSPEEDKEY)
                        val preJson = solJson.getJSONObject(ATMOSPHERICPRESSURESENSORKEY)

                        //get main values for current sol
                        val season = solJson.getString(SEASONKEY)
                        val minDate = sdf.parse(solJson.getString(FIRSTDATEKEY).replace("T", " ").replace("Z", " "))
                        val maxDate = sdf.parse(solJson.getString(LASTDATEKEY).replace("T", " ").replace("Z", " "))

                        if (atJson != null && hwsJson != null && preJson != null) {
                            val at = SolData(
                                SolDataType.ATMOSPHERICTEMPERATURE,
                                atJson.getDouble(AVERAGESAMPLEKEY),
                                atJson.getInt(TOTALSAMPLEKEY),
                                atJson.getDouble(MINDATASAMPLE),
                                atJson.getDouble(MAXDATASAMPLE),
                                ATUNIT)
                            val hws = SolData(
                                SolDataType.HORIZONTALWINDSPEED,
                                hwsJson.getDouble(AVERAGESAMPLEKEY),
                                hwsJson.getInt(TOTALSAMPLEKEY),
                                hwsJson.getDouble(MINDATASAMPLE),
                                hwsJson.getDouble(MAXDATASAMPLE),
                                HWSUNIT)
                            val pre = SolData(
                                SolDataType.HORIZONTALWINDSPEED,
                                preJson.getDouble(AVERAGESAMPLEKEY),
                                preJson.getInt(TOTALSAMPLEKEY),
                                preJson.getDouble(MINDATASAMPLE),
                                preJson.getDouble(MAXDATASAMPLE),
                                PREUNIT)
                            results.add(Sol(item, at, hws, pre, season, minDate, maxDate))
                        }
                    }
                }
                listener.invoke(results.asReversed())
            }
        }, errorListener)
        requestQueue?.add(request)
    }

    companion object {
        const val ROOTKEY = "sol_keys"
        const val ATMOSPHERICTEMPKEY = "AT" //Object; per-Sol atmospheric temperature sensor summary data
        const val HORIZONTALWINDSPEEDKEY = "HWS" //Object; per-Sol horizontal wind speed sensor summary data
        const val ATMOSPHERICPRESSURESENSORKEY = "PRE" //Object; per-Sol atmospheric pressure sensor summary data
        const val WINDDIRECTIONSENSORKEY = "WD" //Object; per-Sol wind direction sensor summary data
        const val SEASONKEY = "Season" //String; per-Sol season on Mars; one of [“winter”, “spring”, “summer”, “fall”]
        const val FIRSTDATEKEY = "First_UTC" //Time of first datum, of any sensor, for the Sol (UTC; YYYY-MM-DDTHH:MM:SSZ)
        const val LASTDATEKEY = "Last_UTC" //Time of last datum, of any sensor, for the Sol (UTC; YYYY-MM-DDTHH:MM:SSZ)

        const val AVERAGESAMPLEKEY = "av"
        const val TOTALSAMPLEKEY = "ct"
        const val MINDATASAMPLE = "mn"
        const val MAXDATASAMPLE = "mx"

        const val ATUNIT = "\u2109"
        const val HWSUNIT = "m/s"
        const val PREUNIT = "Pa"
    }
}