package com.eveexite.coffeemaker.data.firebase

import com.google.firebase.database.DatabaseError

/**
 * Created by ivan on 6/1/17.
 */

class RxFirebaseDataException(error: DatabaseError) : Exception() {

    var error: DatabaseError
        protected set

    init {
        this.error = error
    }

    override fun toString(): String {
        return "RxFirebaseDataException{" +
                "error=" + error +
                '}'
    }

}
