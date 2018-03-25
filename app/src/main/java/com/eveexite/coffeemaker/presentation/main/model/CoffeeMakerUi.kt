package com.eveexite.coffeemaker.presentation.main.model

/**
 * Created by Ivan on 16/02/2018.
 *
 */
data class CoffeeMakerUi(
        var turnOn: Boolean,
        var info: InfoUi,
        var statusCodeUi: StatusCodeUi
) {

    var statusText = makeStatusText()
    var anim: AnimUi = AnimUi(makeFileUri(), playAnimation())

    private fun makeStatusText(): String {
        return when (statusCodeUi) {
            StatusCodeUi.COFFEE_MAKER_UNPLUGGED -> StatusTextUi.COFFEE_MAKER_UNPLUGGED.text
            StatusCodeUi.COFFEE_MAKER_READY -> StatusTextUi.COFFEE_MAKER_READY.text
            StatusCodeUi.NOT_ENOUGH_WATER -> StatusTextUi.NOT_ENOUGH_WATER.text
            StatusCodeUi.COFFEE_READY -> StatusTextUi.COFFEE_READY.text
            StatusCodeUi.PREPARING_COFFEE -> StatusTextUi.PREPARING_COFFEE.text
            StatusCodeUi.COFFEE_MAKER_RESTING -> if (info.timerSleep.trim()
                            .equals("", ignoreCase = true))
                StatusTextUi.COFFEE_MAKER_RESTING.text else
                "Cafetera descansando. Espera ${info.timerSleep} minutos por favor :)"
        }
    }

    private fun makeFileUri(): String {
        return when (statusCodeUi) {
            StatusCodeUi.COFFEE_READY -> "coffee_cup.json"
            else -> "coffee_maker.json"
        }
    }

    private fun playAnimation(): Boolean {
        return when (statusCodeUi) {
            StatusCodeUi.COFFEE_READY, StatusCodeUi.PREPARING_COFFEE -> true
            else -> false
        }
    }

}