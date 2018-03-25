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
        var timerSleep: String,
        var waterLevel: Int,
        private var status: Status = Status()
) {

    var statusCode = checkStatus()

    private fun checkStatus(): StatusCode {
        return if (status.state.equals("offline", true)) {
            StatusCode.COFFEE_MAKER_UNPLUGGED
        } else if (coffeeMakerReady) {
            if (waterLevel > 0) {
                if (!turnOn) StatusCode.COFFEE_MAKER_READY else StatusCode.PREPARING_COFFEE
            } else {
                StatusCode.NOT_ENOUGH_WATER
            }
        } else {
            if (turnOn) {
                if (coffeeReady) StatusCode.COFFEE_READY else StatusCode.PREPARING_COFFEE
            } else {
                StatusCode.COFFEE_MAKER_RESTING
            }
        }
    }

}