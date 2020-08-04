package com.costoda.marsweather.views.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.costoda.marsweather.R;
import com.costoda.marsweather.data.*
import java.lang.Exception

class RvAdapterTemperatureList(private val items: MutableList<Sol>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v : View?
        try {
            v = LayoutInflater.from(parent.context).inflate(R.layout.temperature_card_view, parent, false)
            return TemperatureViewHolder(v)
        }
        catch (e : Exception)
        {
            System.out.println(e.message)
            v = null
        }
        return TemperatureViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val h : TemperatureViewHolder = holder as TemperatureViewHolder
        if (position < items.count()) {
            var item = items[position] as Sol
            h.solName.text = "Sol ${item.name}"
            h.solStartDate.text = item.firstDate.toString()
            h.solEndDate.text = item.lastDate.toString()
            h.solTemperatureHigh.text = "High: ${item.at.maximumData} ${item.at.unit}"
            h.solTemperatureLow.text = "Low: ${item.at.minimumData} ${item.at.unit}"
            h.solTemperatureAverage.text = "Avg: ${item.at.averageSample} ${item.at.unit}"
        }
    }
}

class TemperatureViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView as View){
        private val v = itemView as View
        val solName: TextView = v.findViewById(R.id.solName)
        val solStartDate: TextView = v.findViewById(R.id.solStartDate)
        val solEndDate: TextView = v.findViewById(R.id.solEndDate)
        val solTemperatureHigh: TextView = v.findViewById(R.id.solTemperatureHigh)
        val solTemperatureLow: TextView = v.findViewById(R.id.solTemperatureLow)
        val solTemperatureAverage: TextView = v.findViewById(R.id.solTemperatureAverage)
}