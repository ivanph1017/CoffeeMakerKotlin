package com.eveexite.coffeemaker.domain.model

/**
 * Created by Ivan on 18/03/2018.
 *
 */
enum class StatusCode(val code: Int) {

    COFFEE_MAKER_UNPLUGGED(0),
    COFFEE_MAKER_READY(1),
    NOT_ENOUGH_WATER(2),
    COFFEE_READY(3),
    PREPARING_COFFEE(4),
    COFFEE_MAKER_RESTING(5)

}