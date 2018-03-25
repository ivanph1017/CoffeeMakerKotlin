package com.eveexite.coffeemaker.data.repository.datasource.mapper

/**
 * Created by Ivan on 3/06/2017.
 */

abstract class Mapper<T1, T2> {

    abstract fun map(value: T1): T2

    abstract fun reverseMap(value: T2): T1

}
