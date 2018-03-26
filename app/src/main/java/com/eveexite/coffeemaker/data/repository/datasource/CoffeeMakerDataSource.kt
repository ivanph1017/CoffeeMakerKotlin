package com.eveexite.coffeemaker.data.repository.datasource

import com.eveexite.coffeemaker.data.entity.CoffeeMakerEntity
import com.eveexite.coffeemaker.data.firebase.FirebaseImpl
import com.google.android.gms.tasks.Task

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

/**
 * Created by Ivan on 3/06/2017.
 *
 */

class CoffeeMakerDataSource(private val firebaseImpl: FirebaseImpl) : Datasource {

    override fun getCoffeeMakerEntity(): Flowable<CoffeeMakerEntity> {
        return firebaseImpl.getCoffeeMakerEntity()
    }

    override fun getSingleCoffeeMakerEntity(): Observable<CoffeeMakerEntity> {
        return firebaseImpl.getSingleCoffeeMakerEntity()
    }

    override fun turnOnCoffeeMakerEntity(turnOn: Boolean): Observable<Boolean> {
        val task: Task<Void> = firebaseImpl.turnOnCoffeeMakerEntity(turnOn)
        return Observable.create { ob: ObservableEmitter<Boolean> ->
            task.addOnSuccessListener { ob.onNext(true) }
            task.addOnFailureListener(ob::onError)
            task.addOnCompleteListener({ ob.onComplete() })
        }
    }

}
