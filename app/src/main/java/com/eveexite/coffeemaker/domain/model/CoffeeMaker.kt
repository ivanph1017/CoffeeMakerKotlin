package com.eveexite.coffeemaker.domain.model

/**
 * Created by Ivan on 15/02/2018.
 *
 */

data class CoffeeMaker(
        var turnOn: Boolean,
        var coffeeReady: Boolean,
        var coffeeMakerReady: Boolean,
        var timer: String,
        var timerSleep: Int,
        var waterLevel: Int,
        private var coffeeMakerNetStatus: CoffeeMakerNetStatus = CoffeeMakerNetStatus()
) {

    var status = checkStatus()

    private fun checkStatus(): Status {
        return if (coffeeMakerNetStatus.state.equals("offline", true)) {
            Status.COFFEE_MAKER_UNPLUGGED
        } else if (coffeeMakerReady) {
            if (waterLevel > 0) {
                if (!turnOn) Status.COFFEE_MAKER_READY else Status.PREPARING_COFFEE
            } else {
                Status.NOT_ENOUGH_WATER
            }
        } else {
            if (turnOn) {
                if (coffeeReady) Status.COFFEE_READY else Status.PREPARING_COFFEE
            } else {
                when (timerSleep) {
                    1 -> Status.COFFEE_MAKER_RESTING_1
                    2 -> Status.COFFEE_MAKER_RESTING_2
                    3 -> Status.COFFEE_MAKER_RESTING_3
                    4 -> Status.COFFEE_MAKER_RESTING_4
                    else -> Status.COFFEE_MAKER_RESTING_5 // 5
                }
            }
        }
    }

}