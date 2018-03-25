package com.eveexite.coffeemaker.data.repository.datasource

import com.eveexite.coffeemaker.data.entity.CoffeeMakerEntity

import io.reactivex.Flowable

/**
 * Created by Ivan on 3/06/2017.
 *
 */

interface Datasource {

    fun getCoffeeMakerEntity(): Flowable<CoffeeMakerEntity>

    fun turnOnCoffeeMakerEntity(turnOn: Boolean)

}
