package com.eveexite.coffeemaker.data.entity

/**
 * Created by Ivan on 15/02/2018.
 *
 */

data class CoffeeMakerEntity(
        var turnOn : Boolean = false,
        var coffeeReady : Boolean = false,
        var coffeeMakerReady : Boolean = false,
        var timer : String = "00:00",
        var timerSleep : Int = 5,
        var waterLevel : Int = 0,
        var status: CoffeeMakerNetStatusEntity = CoffeeMakerNetStatusEntity()
)