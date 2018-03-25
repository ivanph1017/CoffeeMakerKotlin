package com.eveexite.coffeemaker.data.repository.datasource.mapper

import com.eveexite.coffeemaker.data.entity.StatusEntity
import com.eveexite.coffeemaker.domain.model.Status
import javax.inject.Inject

/**
 * Created by Ivan on 3/06/2017.
 *
 */

class StatusEntityMapper @Inject constructor(): Mapper<Status, StatusEntity>() {

    override fun map(value: Status): StatusEntity {
        return StatusEntity(value.lastChanged,
                value.state)
    }

    override fun reverseMap(value: StatusEntity): Status {
        return Status(value.lastChanged,
                value.state)
    }

}