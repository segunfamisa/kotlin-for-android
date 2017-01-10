package com.segunfamisa.kotlin.android.domain.mappers

import com.segunfamisa.kotlin.android.data.model.ForecastResult
import com.segunfamisa.kotlin.android.data.model.Forecast as ModelForecast

import com.segunfamisa.kotlin.android.domain.model.Forecast
import com.segunfamisa.kotlin.android.domain.model.ForecastList

import java.text.DateFormat
import java.util.Locale;

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(forecastList: List<ModelForecast>):
            List<Forecast> {
        return forecastList.map {
            convertForecastItemToDomain(it)
        }
    }

    private fun convertForecastItemToDomain(forecast: ModelForecast):
            Forecast {
        return Forecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}