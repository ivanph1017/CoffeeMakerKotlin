package com.eveexite.coffeemaker.domain.usecase

import com.eveexite.coffeemaker.data.repository.CoffeeMakerRepository
import com.eveexite.coffeemaker.domain.model.Status
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ivan on 16/02/2018.
 *
 */
class TurnOnCoffeeMaker @Inject constructor(private val repository: CoffeeMakerRepository) {

    fun execute(): Observable<Boolean> {
        return repository.getSingleCoffeeMaker().flatMap {
            when(it.status) {
                Status.COFFEE_MAKER_READY -> {
                    repository.turnOnCoffeeMaker(true)
                }
                Status.COFFEE_MAKER_UNPLUGGED,
                Status.NOT_ENOUGH_WATER,
                Status.COFFEE_MAKER_RESTING_1,
                Status.COFFEE_MAKER_RESTING_2,
                Status.COFFEE_MAKER_RESTING_3,
                Status.COFFEE_MAKER_RESTING_4,
                Status.COFFEE_MAKER_RESTING_5 -> {
                    throw Exception(it.status.text)
                }
                Status.COFFEE_READY, Status.PREPARING_COFFEE -> {
                    repository.turnOnCoffeeMaker(false)
                }
            }
        }
    }

}