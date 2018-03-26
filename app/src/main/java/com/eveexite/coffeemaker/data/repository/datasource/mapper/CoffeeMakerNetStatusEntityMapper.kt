package com.eveexite.coffeemaker.data.repository.datasource.mapper

import com.eveexite.coffeemaker.data.entity.CoffeeMakerNetStatusEntity
import com.eveexite.coffeemaker.domain.model.CoffeeMakerNetStatus
import javax.inject.Inject

/**
 * Created by Ivan on 3/06/2017.
 *
 */

class CoffeeMakerNetStatusEntityMapper @Inject constructor(): Mapper<CoffeeMakerNetStatus, CoffeeMakerNetStatusEntity>() {

    override fun map(value: CoffeeMakerNetStatus): CoffeeMakerNetStatusEntity {
        return CoffeeMakerNetStatusEntity(value.lastChanged,
                value.state)
    }

    override fun reverseMap(value: CoffeeMakerNetStatusEntity): CoffeeMakerNetStatus {
        return CoffeeMakerNetStatus(value.lastChanged,
                value.state)
    }

}