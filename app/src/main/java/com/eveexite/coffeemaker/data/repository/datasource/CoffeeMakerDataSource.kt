package com.eveexite.coffeemaker.data.repository.datasource

import com.eveexite.coffeemaker.data.entity.CoffeeMakerEntity
import com.eveexite.coffeemaker.data.firebase.FirebaseImpl

import io.reactivex.Flowable

/**
 * Created by Ivan on 3/06/2017.
 *
 */

class CoffeeMakerDataSource(private val firebaseImpl: FirebaseImpl) : Datasource {

    override fun getCoffeeMakerEntity(): Flowable<CoffeeMakerEntity> {
        return firebaseImpl.getCoffeeMakerEntity()
    }

    override fun turnOnCoffeeMakerEntity(turnOn: Boolean) {
        firebaseImpl.turnOnCoffeeMakerEntity(turnOn)
    }

}
