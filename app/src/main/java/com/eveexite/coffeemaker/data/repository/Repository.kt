package com.eveexite.coffeemaker.data.repository

import com.eveexite.coffeemaker.domain.model.CoffeeMaker

import io.reactivex.Flowable

/**
 * Created by Ivan on 3/06/2017.
 *
 */

interface Repository {

    fun getCoffeeMaker(): Flowable<CoffeeMaker>

    fun turnOnCoffeeMaker(turnOn: Boolean)

}
