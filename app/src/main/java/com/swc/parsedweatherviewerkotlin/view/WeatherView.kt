package com.swc.parsedweatherviewerkotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import com.swc.parsedweatherviewerkotlin.R
import com.swc.parsedweatherviewerkotlin.model.WeatherElement
import com.swc.parsedweatherviewerkotlin.utils.LoggingUtils
import kotlinx.android.synthetic.main.weather_element.view.*

class WeatherView : LinearLayout {
    private var weatherElem: WeatherElement? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initViews(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initViews(context)
    }

    private fun initViews(context: Context) {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.weather_element, this)
    }

    fun setWeatherElem(weatherElem: WeatherElement) {
        this.weatherElem = weatherElem
        tvSummary.text = weatherElem.summary
        tvTemp.text = weatherElem.temp
        tvRain.text = weatherElem.rainChance

        Picasso.get()
                .load(weatherElem.iconUrl)
                .resize(200, 200)
                .centerInside()
                .into(ivWeather)
    }

    companion object {

        private val TAG = "WeatherView"
    }
}
