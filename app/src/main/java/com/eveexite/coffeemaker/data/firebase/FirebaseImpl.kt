package com.eveexite.coffeemaker.data.firebase

import com.eveexite.coffeemaker.data.entity.CoffeeMakerEntity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ivan on 3/06/2017.
 *
 */

class FirebaseImpl {

    init {
        FirebaseDatabase.getInstance().goOnline()
    }

    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.root

    fun getCoffeeMakerEntity(): Flowable<CoffeeMakerEntity> {
        return RxFirebaseDatabase.flowableValueEvent(databaseReference,
                DataSnapshotMapper.of(CoffeeMakerEntity::class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun turnOnCoffeeMakerEntity(turnOn: Boolean) {
        databaseReference.child("turnOn").setValue(turnOn)
    }

}
