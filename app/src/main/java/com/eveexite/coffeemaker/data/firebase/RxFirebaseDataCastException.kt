package com.eveexite.coffeemaker.data.firebase

/**
 * Created by ivan on 6/1/17.
 */

class RxFirebaseDataCastException : Exception {

    constructor() {}

    constructor(detailMessage: String) : super(detailMessage) {}

    constructor(detailMessage: String, throwable: Throwable) : super(detailMessage, throwable) {}

    constructor(throwable: Throwable) : super(throwable) {}

}
