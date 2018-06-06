package com.swc.parsedweatherviewerkotlin.model

/**
 * Table row with Region, AM-Weather, PM-Weather
 */
class WeatherRow(val region: String, val amWeather: WeatherElement, val pmWeather: WeatherElement)
