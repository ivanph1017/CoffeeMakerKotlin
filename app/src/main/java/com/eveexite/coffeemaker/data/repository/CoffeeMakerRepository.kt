package com.eveexite.coffeemaker.data.repository

import com.eveexite.coffeemaker.data.repository.datasource.CoffeeMakerDataSourceFactory
import com.eveexite.coffeemaker.data.repository.datasource.Datasource
import com.eveexite.coffeemaker.data.repository.datasource.mapper.CoffeeMakerEntityMapper
import com.eveexite.coffeemaker.domain.model.CoffeeMaker
import io.reactivex.Flowable
import io.reactivex.annotations.NonNull
import javax.inject.Inject

/**
 * Created by Ivan on 3/06/2017.
 *
 */

class CoffeeMakerRepository @Inject
constructor(@NonNull coffeeMakerDataSourceFactory: CoffeeMakerDataSourceFactory,
                     @param:NonNull private val mapper: CoffeeMakerEntityMapper) : Repository {

    private val datasource: Datasource = coffeeMakerDataSourceFactory.createDataSource()

    override fun getCoffeeMaker(): Flowable<CoffeeMaker> {
        return datasource.getCoffeeMakerEntity()
                .map { coffeeMakerEntity -> mapper.reverseMap(coffeeMakerEntity) }
    }

    override fun turnOnCoffeeMaker(turnOn: Boolean) {
        datasource.turnOnCoffeeMakerEntity(turnOn)
    }

}
