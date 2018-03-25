package com.eveexite.coffeemaker.data.repository.datasource.mapper

import com.eveexite.coffeemaker.data.entity.CoffeeMakerEntity
import com.eveexite.coffeemaker.domain.model.CoffeeMaker
import javax.inject.Inject

/**
 * Created by Ivan on 3/06/2017.
 *
 */

class CoffeeMakerEntityMapper @Inject
constructor(private val mapper: StatusEntityMapper): Mapper<CoffeeMaker, CoffeeMakerEntity>() {

    override fun map(value: CoffeeMaker): CoffeeMakerEntity {
        return CoffeeMakerEntity(value.turnOn,
                value.coffeeReady,
                value.coffeeMakerReady,
                value.timer,
                value.timerSleep,
                value.waterLevel)
    }

    override fun reverseMap(value: CoffeeMakerEntity): CoffeeMaker {
        return CoffeeMaker(value.turnOn,
                value.coffeeReady,
                value.coffeeMakerReady,
                value.timer,
                value.timerSleep,
                value.waterLevel,
                mapper.reverseMap(value.status))
    }

}
