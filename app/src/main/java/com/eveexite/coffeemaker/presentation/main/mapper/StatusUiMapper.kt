package com.eveexite.coffeemaker.presentation.main.mapper

import com.eveexite.coffeemaker.data.repository.datasource.mapper.Mapper
import com.eveexite.coffeemaker.domain.model.Status
import com.eveexite.coffeemaker.presentation.main.model.StatusUi
import javax.inject.Inject

/**
 * Created by Ivan on 18/03/2018.
 *
 */

class StatusUiMapper @Inject
internal constructor(): Mapper<StatusUi, Status>() {

    override fun map(value: StatusUi): Status {
        return when (value) {
            StatusUi.COFFEE_MAKER_UNPLUGGED -> Status.COFFEE_MAKER_UNPLUGGED
            StatusUi.COFFEE_MAKER_READY -> Status.COFFEE_MAKER_READY
            StatusUi.NOT_ENOUGH_WATER -> Status.NOT_ENOUGH_WATER
            StatusUi.COFFEE_READY -> Status.COFFEE_READY
            StatusUi.PREPARING_COFFEE -> Status.PREPARING_COFFEE
            StatusUi.COFFEE_MAKER_RESTING_1 -> Status.COFFEE_MAKER_RESTING_1
            StatusUi.COFFEE_MAKER_RESTING_2 -> Status.COFFEE_MAKER_RESTING_2
            StatusUi.COFFEE_MAKER_RESTING_3 -> Status.COFFEE_MAKER_RESTING_3
            StatusUi.COFFEE_MAKER_RESTING_4 -> Status.COFFEE_MAKER_RESTING_4
            StatusUi.COFFEE_MAKER_RESTING_5 -> Status.COFFEE_MAKER_RESTING_5
        }
    }

    override fun reverseMap(value: Status): StatusUi {
        return when (value) {
            Status.COFFEE_MAKER_UNPLUGGED -> StatusUi.COFFEE_MAKER_UNPLUGGED
            Status.COFFEE_MAKER_READY -> StatusUi.COFFEE_MAKER_READY
            Status.NOT_ENOUGH_WATER -> StatusUi.NOT_ENOUGH_WATER
            Status.COFFEE_READY -> StatusUi.COFFEE_READY
            Status.PREPARING_COFFEE -> StatusUi.PREPARING_COFFEE
            Status.COFFEE_MAKER_RESTING_1 -> StatusUi.COFFEE_MAKER_RESTING_1
            Status.COFFEE_MAKER_RESTING_2 -> StatusUi.COFFEE_MAKER_RESTING_2
            Status.COFFEE_MAKER_RESTING_3 -> StatusUi.COFFEE_MAKER_RESTING_3
            Status.COFFEE_MAKER_RESTING_4 -> StatusUi.COFFEE_MAKER_RESTING_4
            Status.COFFEE_MAKER_RESTING_5 -> StatusUi.COFFEE_MAKER_RESTING_5
        }
    }

}