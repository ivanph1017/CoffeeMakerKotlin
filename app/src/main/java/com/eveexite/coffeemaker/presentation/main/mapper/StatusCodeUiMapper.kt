package com.eveexite.coffeemaker.presentation.main.mapper

import com.eveexite.coffeemaker.data.repository.datasource.mapper.Mapper
import com.eveexite.coffeemaker.domain.model.StatusCode
import com.eveexite.coffeemaker.presentation.main.model.StatusCodeUi
import javax.inject.Inject

/**
 * Created by Ivan on 18/03/2018.
 *
 */

class StatusCodeUiMapper @Inject
internal constructor(): Mapper<StatusCodeUi, StatusCode>() {

    override fun map(value: StatusCodeUi): StatusCode {
        return when (value) {
            StatusCodeUi.COFFEE_MAKER_UNPLUGGED -> StatusCode.COFFEE_MAKER_UNPLUGGED
            StatusCodeUi.COFFEE_MAKER_READY -> StatusCode.COFFEE_MAKER_READY
            StatusCodeUi.NOT_ENOUGH_WATER -> StatusCode.NOT_ENOUGH_WATER
            StatusCodeUi.COFFEE_READY -> StatusCode.COFFEE_READY
            StatusCodeUi.PREPARING_COFFEE -> StatusCode.PREPARING_COFFEE
            StatusCodeUi.COFFEE_MAKER_RESTING -> StatusCode.COFFEE_MAKER_RESTING
        }
    }

    override fun reverseMap(value: StatusCode): StatusCodeUi {
        return when (value) {
            StatusCode.COFFEE_MAKER_UNPLUGGED -> StatusCodeUi.COFFEE_MAKER_UNPLUGGED
            StatusCode.COFFEE_MAKER_READY -> StatusCodeUi.COFFEE_MAKER_READY
            StatusCode.NOT_ENOUGH_WATER -> StatusCodeUi.NOT_ENOUGH_WATER
            StatusCode.COFFEE_READY -> StatusCodeUi.COFFEE_READY
            StatusCode.PREPARING_COFFEE -> StatusCodeUi.PREPARING_COFFEE
            StatusCode.COFFEE_MAKER_RESTING -> StatusCodeUi.COFFEE_MAKER_RESTING
        }
    }

}