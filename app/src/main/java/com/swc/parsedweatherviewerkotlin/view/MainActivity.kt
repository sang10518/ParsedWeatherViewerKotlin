package com.swc.parsedweatherviewerkotlin.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.swc.parsedweatherviewerkotlin.R
import com.swc.parsedweatherviewerkotlin.model.WeatherRow
import com.swc.parsedweatherviewerkotlin.utils.*
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
        rvWeatherTable.layoutManager = LinearLayoutManager(this)
        rvWeatherTable.setHasFixedSize(true)

        pbLoading.indeterminateDrawable.setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY)
    }

    private fun startFetchWeather() {
        //Todo: improve loading state UX

        pbLoading.visibility = View.VISIBLE

        Thread(Runnable {
            val result = CommonUtils.getTodayWeatherTable(WEATHER_URL, WEATHER_DOCUMENT_QUERY)

            if (result != null) {
                setWeatherView(result)
            } else {
                setNetworkErrorView()
            }
        }).start()
    }

    private fun setNetworkErrorView() {
        pbLoading.visibility = View.GONE
        rvWeatherTable.visibility = View.GONE
        llNetworkError.visibility = View.VISIBLE
    }

    private fun setWeatherView(result: List<WeatherRow>) {
        runOnUiThread {
            val adapter = WeatherRowAdapter(result)
            rvWeatherTable.adapter = adapter

            pbLoading.visibility = View.GONE
            llNetworkError.visibility = View.GONE
            rvWeatherTable.visibility = View.VISIBLE
        }
    }
}