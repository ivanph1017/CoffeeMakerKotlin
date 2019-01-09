package com.eveexite.coffeemaker.data.firebase

import com.google.firebase.database.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import io.reactivex.functions.Function
import kotlin.reflect.KClass


/**
 * Created by ivan on 6/1/17.
 *
 */

object RxFirebaseDatabase {

    fun flowableValueEvent(query: Query): Flowable<DataSnapshot> {
        return Flowable.create({ fe: FlowableEmitter<DataSnapshot> ->
            val valueEventListener = query.addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            fe.onNext(dataSnapshot)
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            fe.onError(RxFirebaseDataException(databaseError))
                        }
                    }
            )
            fe.setDisposable(Disposables.fromAction { query.removeEventListener(valueEventListener) })
        }, BackpressureStrategy.BUFFER)
    }

    fun observableSingleValueEvent(query: Query): Observable<DataSnapshot> {
        return Observable.create { oe ->
            query.addListenerForSingleValueEvent(
                    object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            oe.onNext(dataSnapshot)
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            oe.onError(RxFirebaseDataException(databaseError))
                        }
                    }
            )
        }
    }

    fun flowableChildEvent(
            query: Query): Flowable<RxFirebaseChildEvent<DataSnapshot>> {
        return Flowable.create({ fe ->
            val childEventListener = query.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    fe.onNext(RxFirebaseChildEvent(dataSnapshot.key!!,
                            dataSnapshot,
                            previousChildName!!,
                            RxFirebaseChildEvent.EventType.ADDED
                    ))
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    fe.onNext(RxFirebaseChildEvent(dataSnapshot.key!!,
                            dataSnapshot,
                            previousChildName!!,
                            RxFirebaseChildEvent.EventType.CHANGED
                    ))
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    fe.onNext(RxFirebaseChildEvent(dataSnapshot.key!!,
                            dataSnapshot,
                            RxFirebaseChildEvent.EventType.REMOVED
                    ))
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    fe.onNext(RxFirebaseChildEvent(dataSnapshot.key!!,
                            dataSnapshot,
                            previousChildName!!,
                            RxFirebaseChildEvent.EventType.MOVED
                    ))
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    fe.onError(RxFirebaseDataException(databaseError))
                }


            })
            fe.setDisposable(Disposables.fromAction { query.removeEventListener(childEventListener) })
        }, BackpressureStrategy.BUFFER
        )
    }

    fun <T: Any> flowableValueEvent(query: Query,
                               mapper: Function<in DataSnapshot, out T>): Flowable<T> {
        return flowableValueEvent(query).map(mapper)
    }

    fun <T: Any> observableSingleValueEvent(query: Query,
                                       mapper: Function<in DataSnapshot, out T>): Observable<T> {
        return observableSingleValueEvent(query).map(mapper)
    }

    fun <T: Any> flowableChildEvent(
            query: Query, mapper: Function<in RxFirebaseChildEvent<DataSnapshot>, out RxFirebaseChildEvent<T>>): Flowable<RxFirebaseChildEvent<T>> {
        return flowableChildEvent(query).map(mapper)
    }

    fun <T: Any> flowableValueEvent(query: Query,
                               kClass: KClass<T>): Flowable<T> {
        return flowableValueEvent(query, DataSnapshotMapper.of(kClass))
    }

    fun <T: Any> observableSingleValueEvent(query: Query,
                                            kClass: KClass<T>): Observable<T> {
        return observableSingleValueEvent(query, DataSnapshotMapper.of(kClass))
    }

    fun <T: Any> flowableChildEvent(
            query: Query, kClass: KClass<T>): Flowable<RxFirebaseChildEvent<T>> {
        return flowableChildEvent(query, DataSnapshotMapper.ofChildEvent(kClass))
    }

}
