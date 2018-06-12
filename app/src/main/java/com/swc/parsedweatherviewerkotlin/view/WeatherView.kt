package com.swc.parsedweatherviewerkotlin.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
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

    constructor(context: Context) : super(context) {
        LoggingUtils.e(TAG, "weatherview init 1")
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LoggingUtils.e(TAG, "weatherview init 2")
        initViews(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initViews(context)
        LoggingUtils.e(TAG, "weatherview init 3")
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initViews(context)
        LoggingUtils.e(TAG, "weatherview init 4")
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
