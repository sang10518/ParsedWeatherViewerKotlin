package com.swc.parsedweatherviewerkotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.swc.parsedweatherviewerkotlin.R
import com.swc.parsedweatherviewerkotlin.model.WeatherRow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        startFetchWeather()
    }


    private fun initViews() {
        rvWeatherTable.setLayoutManager(LinearLayoutManager(this@MainActivity))
        rvWeatherTable.setHasFixedSize(true)
    }

    private fun startFetchWeather() {
        //Todo: improve loading state UX
        Thread(Runnable {
            LoggingUtils.e(TAG, "startFetchWeather")
            val result = CommonUtils.getTodayWeatherTable(Constants.WEATHER_URL, Constants.WEATHER_DOCUMENT_QUERY)
            LoggingUtils.e(TAG, "finishFetchWeather, result: $result")

            setWeatherView(result)
        }).start()
    }

    private fun setWeatherView(result: List<WeatherRow>) {
        for (weatherRow in result) {
            LoggingUtils.e(TAG, "region : " + weatherRow.region + " AM, rain:" + weatherRow.amWeather.rainChance + ", temp: " + weatherRow.amWeather.temp + ", summary: " + weatherRow.amWeather.summary)
            LoggingUtils.e(TAG, "region : " + weatherRow.region + " PM, rain:" + weatherRow.pmWeather.rainChance + ", temp: " + weatherRow.pmWeather.temp + ", summary: " + weatherRow.pmWeather.summary)
        }

        runOnUiThread {
            val adapter = WeatherRowAdapter(result)
            rvWeatherTable.setAdapter(adapter)
        }

    }
}