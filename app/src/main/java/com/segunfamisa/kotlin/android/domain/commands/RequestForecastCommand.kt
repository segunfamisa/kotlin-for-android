package com.segunfamisa.kotlin.android.domain.commands

import com.segunfamisa.kotlin.android.data.ForecastRequest
import com.segunfamisa.kotlin.android.domain.mappers.ForecastDataMapper
import com.segunfamisa.kotlin.android.domain.model.ForecastList

class RequestForecastCommand(val zipCode: String): Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}