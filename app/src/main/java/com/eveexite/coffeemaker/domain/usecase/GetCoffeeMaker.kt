package com.eveexite.coffeemaker.domain.usecase

import com.eveexite.coffeemaker.data.repository.CoffeeMakerRepository
import com.eveexite.coffeemaker.domain.model.CoffeeMaker
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Ivan on 16/02/2018.
 *
 */
class GetCoffeeMaker @Inject constructor(private val repository: CoffeeMakerRepository){

    fun get(): Flowable<CoffeeMaker> {
        return repository.getCoffeeMaker()
    }

}