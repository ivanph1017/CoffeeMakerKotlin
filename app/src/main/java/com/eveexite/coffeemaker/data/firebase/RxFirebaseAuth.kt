package com.eveexite.coffeemaker.data.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable

/**
 * Created by ivan on 6/1/17.
 */

object RxFirebaseAuth {

    fun signInWithEmailAndPassword(firebaseAuth: FirebaseAuth,
                                   email: String,
                                   password: String): Observable<AuthResult> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseAuth.signInWithEmailAndPassword(email, password)) }
    }

}
