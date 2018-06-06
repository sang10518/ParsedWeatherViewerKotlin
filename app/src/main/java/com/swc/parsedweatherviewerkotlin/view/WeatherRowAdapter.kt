package com.swc.parsedweatherviewerkotlin.view

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.swc.parsedweatherviewerkotlin.R
import com.swc.parsedweatherviewerkotlin.model.WeatherRow

class WeatherRowAdapter(private val mWeatherRows: List<WeatherRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_weather_header, parent, false)
            return WeatherHeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_weather, parent, false)
            return WeatherViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == TYPE_ITEM) {
            //To account for header, we fetch position -1.
            val `object` = mWeatherRows[position - 1]

            val region = `object`.region
            LoggingUtils.e(TAG, "region at position $position: $region")
            (holder as WeatherViewHolder).tvRegion.setText(region)
            holder.wvAmWeather.setWeatherElem(`object`.amWeather)
            holder.wvPmWeather.setWeatherElem(`object`.pmWeather)
        }
    }

    override fun getItemCount(): Int {
        return mWeatherRows.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) TYPE_HEADER else TYPE_ITEM

    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    inner class WeatherViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvRegion: TextView
        internal var wvAmWeather: WeatherView
        internal var wvPmWeather: WeatherView

        init {
            tvRegion = itemView.findViewById(R.id.tvRegion)
            wvAmWeather = itemView.findViewById(R.id.wvAmWeather)
            wvPmWeather = itemView.findViewById(R.id.wvPmWeather)
        }
    }

    inner class WeatherHeaderViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvRegionTitle: TextView
        internal var tvAmWeatherTitle: TextView
        internal var tvPmWeatherTitle: TextView

        init {
            itemView.setBackgroundColor(Color.LTGRAY)
            tvRegionTitle = itemView.findViewById(R.id.tvRegionTitle)
            tvAmWeatherTitle = itemView.findViewById(R.id.tvAmWeatherTitle)
            tvPmWeatherTitle = itemView.findViewById(R.id.tvPmWeatherTitle)
        }
    }

    companion object {

        private val TAG = "WeatherRowAdapter"
        private val TYPE_HEADER = 0
        private val TYPE_ITEM = 1
    }


}
