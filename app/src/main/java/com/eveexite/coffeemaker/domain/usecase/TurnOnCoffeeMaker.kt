package com.eveexite.coffeemaker.domain.usecase

import com.eveexite.coffeemaker.data.repository.CoffeeMakerRepository
import javax.inject.Inject

/**
 * Created by Ivan on 16/02/2018.
 *
 */
class TurnOnCoffeeMaker @Inject constructor(private val repository: CoffeeMakerRepository) {

    fun turnOn(turnOn: Boolean) {
        repository.turnOnCoffeeMaker(turnOn)
    }

}