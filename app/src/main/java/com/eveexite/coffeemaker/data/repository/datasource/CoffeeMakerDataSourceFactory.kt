package com.eveexite.coffeemaker.data.repository.datasource

import com.eveexite.coffeemaker.data.firebase.FirebaseImpl
import javax.inject.Inject

/**
 * Created by Ivan on 3/06/2017.
 */

class CoffeeMakerDataSourceFactory @Inject constructor() {

    fun createDataSource(): CoffeeMakerDataSource {
        return CoffeeMakerDataSource(FirebaseImpl())
    }

}
