package com.eveexite.coffeemaker.domain.usecase

import com.eveexite.coffeemaker.data.repository.CoffeeMakerRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ivan on 16/02/2018.
 *
 */
class CheckTurnOn @Inject constructor(private val repository: CoffeeMakerRepository){

    fun execute(): Observable<String> {
        return repository.getSingleCoffeeMaker().map {
            if (it.turnOn) "¿Deseas apagar la cafetera?" else "¿Deseas encender la cafetera?"
        }
    }

}