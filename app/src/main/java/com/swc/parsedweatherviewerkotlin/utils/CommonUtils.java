package com.swc.parsedweatherviewerkotlin.utils;

import com.swc.parsedweatherviewerkotlin.model.WeatherElement;
import com.swc.parsedweatherviewerkotlin.model.WeatherRow;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    private static final String TAG = "CommonUtils";

    /**
     *
     * @param url
     * @param query
     * @return
     */
    public static List<WeatherRow> getTodayWeatherTable(String url, String query) {

        List<WeatherRow> weatherTable = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select(query).first();

            for (Element row : table.select("tr")) {
                //for each row. get region, morning, afternoon table elements
                WeatherElement amWeather = null, pmWeather = null;
                Element region = row.select("th").first();

                Elements icons = row.getElementsByClass("icon");
                Elements summaries = row.getElementsByClass("nm");
                Elements rains = row.getElementsByClass("rain");
                Elements temps = row.getElementsByClass("temp");

                if (icons.size() > 1 && summaries.size() > 1 && rains.size() > 1 && temps.size() > 1){
                    amWeather = new WeatherElement(summaries.get(0).text(), rains.get(0).text(), temps.get(0).text(), icons.get(0).select("img").first().attr("abs:src"));
                    pmWeather = new WeatherElement(summaries.get(1).text(), rains.get(1).text(), temps.get(1).text(), icons.get(1).select("img").first().attr("abs:src"));
                    weatherTable.add(new WeatherRow(region.text(), amWeather, pmWeather));
                }
            }
        } catch (IOException e) {

        }

        return weatherTable;

    }
}
