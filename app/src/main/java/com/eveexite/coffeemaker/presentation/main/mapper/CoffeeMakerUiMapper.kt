package com.eveexite.coffeemaker.presentation.main.mapper

import com.eveexite.coffeemaker.data.repository.datasource.mapper.Mapper
import com.eveexite.coffeemaker.domain.model.CoffeeMaker
import com.eveexite.coffeemaker.presentation.main.model.CoffeeMakerUi
import com.eveexite.coffeemaker.presentation.main.model.InfoUi
import javax.inject.Inject

/**
 * Created by Ivan on 16/02/2018.
 *
 */
class CoffeeMakerUiMapper @Inject
internal constructor(private val mapper: StatusCodeUiMapper): Mapper<CoffeeMakerUi, CoffeeMaker>(){

    override fun map(value: CoffeeMakerUi): CoffeeMaker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reverseMap(value: CoffeeMaker): CoffeeMakerUi {
        return CoffeeMakerUi(value.turnOn,
                InfoUi(value.timer, value.timerSleep, value.waterLevel),
                mapper.reverseMap(value.statusCode))
    }

}