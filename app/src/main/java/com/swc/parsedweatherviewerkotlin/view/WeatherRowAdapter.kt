package com.swc.parsedweatherviewerkotlin.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.swc.parsedweatherviewerkotlin.R
import com.swc.parsedweatherviewerkotlin.model.WeatherRow
import com.swc.parsedweatherviewerkotlin.utils.LoggingUtils
import kotlinx.android.synthetic.main.row_weather.view.*
import kotlinx.android.synthetic.main.row_weather_header.view.*

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
            (holder as WeatherViewHolder).tvRegion.text = region
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
            tvRegion = itemView.tvRegion
            wvAmWeather = itemView.wvAmWeather
            wvPmWeather = itemView.wvPmWeather
        }
    }

    inner class WeatherHeaderViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvRegionTitle: TextView
        internal var tvAmWeatherTitle: TextView
        internal var tvPmWeatherTitle: TextView

        init {
            itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.colorHeader))
            tvRegionTitle = itemView.tvRegionTitle
            tvAmWeatherTitle = itemView.tvAmWeatherTitle
            tvPmWeatherTitle = itemView.tvPmWeatherTitle
        }
    }

    companion object {

        private val TAG = "WeatherRowAdapter"
        private val TYPE_HEADER = 0
        private val TYPE_ITEM = 1
    }


}
