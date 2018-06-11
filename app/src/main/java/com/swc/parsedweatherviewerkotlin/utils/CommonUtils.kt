package com.swc.parsedweatherviewerkotlin.utils

import com.swc.parsedweatherviewerkotlin.model.WeatherElement
import com.swc.parsedweatherviewerkotlin.model.WeatherRow
import org.jsoup.Jsoup
import java.io.IOException
import java.util.ArrayList

/**
 * Created by sangwonc on 2018. 6. 11..
 */

object CommonUtils {

    val TAG = "CommonUtils"

    /**
     *
     * @param url
     * @param query
     * @return
     */
    fun getTodayWeatherTable(url: String, query: String): List<WeatherRow> {

        val weatherTable = ArrayList<WeatherRow>()
        try {
            val doc = Jsoup.connect(url).get()
            val table = doc.select(query).first()

            for (row in table.select("tr")) {
                //for each row. get region, morning, afternoon table elements
                var amWeather: WeatherElement? = null
                var pmWeather: WeatherElement? = null
                val region = row.select("th").first()

                val icons = row.getElementsByClass("icon")
                val summaries = row.getElementsByClass("nm")
                val rains = row.getElementsByClass("rain")
                val temps = row.getElementsByClass("temp")

                if (icons.size > 1 && summaries.size > 1 && rains.size > 1 && temps.size > 1) {
                    amWeather = WeatherElement(summaries[0].text(), rains[0].text(), temps[0].text(), icons[0].select("img").first().attr("abs:src"))
                    pmWeather = WeatherElement(summaries[1].text(), rains[1].text(), temps[1].text(), icons[1].select("img").first().attr("abs:src"))
                    weatherTable.add(WeatherRow(region.text(), amWeather, pmWeather))
                }
            }
        } catch (e: IOException) {

        }

        return weatherTable

    }
}
