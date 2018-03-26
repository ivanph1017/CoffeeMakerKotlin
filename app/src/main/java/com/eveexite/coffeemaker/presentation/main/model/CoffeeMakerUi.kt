package com.eveexite.coffeemaker.presentation.main.model

/**
 * Created by Ivan on 16/02/2018.
 *
 */
data class CoffeeMakerUi(
        var turnOn: Boolean,
        var infoUi: InfoUi,
        var statusUi: StatusUi
) {

    var animUi: AnimUi = AnimUi(makeFileUri(), playAnimation())

    private fun makeFileUri(): String {
        return when (statusUi) {
            StatusUi.COFFEE_READY -> "coffee_cup.json"
            else -> "coffee_maker.json"
        }
    }

    private fun playAnimation(): Boolean {
        return when (statusUi) {
            StatusUi.COFFEE_READY, StatusUi.PREPARING_COFFEE -> true
            else -> false
        }
    }

}