package com.eveexite.coffeemaker.domain.model

/**
 * Created by Ivan on 23/03/2018.
 *
 */
data class Status(
        var lastChanged: Long = 0,
        var state: String = "offline"
)