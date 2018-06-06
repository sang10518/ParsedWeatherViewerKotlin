package com.swc.parsedweatherviewerkotlin.model

/**
 * Table row with Region, AM-Weather, PM-Weather
 */
class WeatherElement(val summary: String //e.g. 흐림
                     , val rainChance: String // 강수확률 30%
                     , val temp: String // 기온 19.0℃
                     , val iconUrl: String)
