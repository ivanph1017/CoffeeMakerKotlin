package com.eveexite.coffeemaker.data.firebase

import com.google.firebase.auth.*
import io.reactivex.Observable

/**
 * Created by ivan on 6/1/17.
 */

object RxFirebaseUser {

    fun getIdToken(firebaseUser: FirebaseUser,
                 forceRefresh: Boolean): Observable<GetTokenResult> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseUser.getIdToken(forceRefresh)) }
    }

    fun updateEmail(firebaseUser: FirebaseUser,
                    email: String): Observable<Void> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseUser.updateEmail(email)) }
    }

    fun updatePassword(firebaseUser: FirebaseUser,
                       password: String): Observable<Void> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseUser.updatePassword(password)) }
    }

    fun updateProfile(firebaseUser: FirebaseUser,
                      request: UserProfileChangeRequest): Observable<Void> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseUser.updateProfile(request)) }
    }

    fun delete(firebaseUser: FirebaseUser): Observable<Void> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseUser.delete()) }
    }

    fun reauthenticate(firebaseUser: FirebaseUser,
                       credential: AuthCredential): Observable<Void> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseUser.reauthenticate(credential)) }
    }

    fun linkWithCredential(firebaseUser: FirebaseUser,
                           credential: AuthCredential): Observable<AuthResult> {
        return Observable.create { oe -> RxHandler.assignOnTask(oe, firebaseUser.linkWithCredential(credential)) }
    }

}
