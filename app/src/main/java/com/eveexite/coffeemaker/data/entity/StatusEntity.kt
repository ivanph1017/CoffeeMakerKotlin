package com.eveexite.coffeemaker.data.entity

/**
 * Created by Ivan on 23/03/2018.
 *
 */
data class StatusEntity(
        var lastChanged: Long = 0,
        var state: String = "offline"
)