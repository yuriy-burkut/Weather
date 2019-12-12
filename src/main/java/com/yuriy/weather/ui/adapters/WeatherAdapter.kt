package com.yuriy.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuriy.weather.R
import com.yuriy.weather.data.entities.ForecastItem
import com.yuriy.weather.data.network.responces.ForecastResponse
import kotlinx.android.synthetic.main.list_item.view.*

class WeatherAdapter(private val forecast: ForecastResponse) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecast.list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecast.list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ForecastItem) = with(itemView) {
            id_time.text = item.dtTxt
            id_temp.text = item.main.temp.toString()
            id_humidity.text = item.main.humidity.toString()
            id_pressure.text = item.main.pressure.toString()

            id_conditions.text = when (item.weatherInfo[0].conditions) {
                "Clouds" -> context.getString(R.string.clouds, item.clouds.all)
                "Rain" -> context.getString(R.string.rain, item.rain?.h, "mm")
                "Snow" -> context.getString(R.string.snow, item.snow?.h, "mm")
                else -> context.getString(R.string.clear)
            }

        }
    }
}
