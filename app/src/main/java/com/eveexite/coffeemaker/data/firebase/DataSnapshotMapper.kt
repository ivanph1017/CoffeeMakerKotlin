package com.eveexite.coffeemaker.data.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.GenericTypeIndicator
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.Function
import kotlin.reflect.KClass

/**
 * Created by ivan on 6/1/17.
 *
 */

abstract class DataSnapshotMapper<T, U: Any> : Function<T, U> {

    private class TypedDataSnapshotMapper<U: Any>(private val kClass: KClass<U>)
        : DataSnapshotMapper<DataSnapshot, U>() {

        @Throws(Exception::class)
        override fun apply(dataSnapshot: DataSnapshot): U? {
            return if (dataSnapshot.exists()) {
                getDataSnapshotTypedValue(dataSnapshot, kClass)
            } else {
                null
            }
        }
    }

    private class TypedListDataSnapshotMapper<U: Any>(private val kClass: KClass<U>)
        : DataSnapshotMapper<DataSnapshot, List<U>>() {

        @Throws(Exception::class)
        override fun apply(dataSnapshot: DataSnapshot): List<U> {
            val items = ArrayList<U>()
            for (childSnapshot in dataSnapshot.children) {
                items.add(getDataSnapshotTypedValue(childSnapshot, kClass))
            }
            return items
        }
    }

    private class TypedMapDataSnapshotMapper<U: Any>(private val kClass: KClass<U>)
        : DataSnapshotMapper<DataSnapshot, LinkedHashMap<String, U>>() {

        @Throws(Exception::class)
        override fun apply(dataSnapshot: DataSnapshot): LinkedHashMap<String, U> {
            val items = LinkedHashMap<String, U>()
            for (childSnapshot in dataSnapshot.children) {
                items[childSnapshot.key] = getDataSnapshotTypedValue(childSnapshot, kClass)
            }
            return items
        }
    }

    private class GenericTypedDataSnapshotMapper<U: Any>(private val genericTypeIndicator: GenericTypeIndicator<U>)
        : DataSnapshotMapper<DataSnapshot, U>() {

        @Throws(Exception::class)
        override fun apply(dataSnapshot: DataSnapshot): U? {
            return if (dataSnapshot.exists()) {
                dataSnapshot.getValue(genericTypeIndicator) ?: throw Exceptions.propagate(Exception(
                        "unable to cast firebase data response to generic type"))
            } else {
                null
            }
        }
    }

    private class ChildEventDataSnapshotMapper<U: Any>(private val kClass: KClass<U>)
        : DataSnapshotMapper<RxFirebaseChildEvent<DataSnapshot>, RxFirebaseChildEvent<U>>() {

        @Throws(Exception::class)
        override fun apply(t: RxFirebaseChildEvent<DataSnapshot>): RxFirebaseChildEvent<U> {
            val dataSnapshot: DataSnapshot? = t.value
            return if (dataSnapshot!!.exists()) {
                RxFirebaseChildEvent<U>(
                        dataSnapshot.key,
                        getDataSnapshotTypedValue(dataSnapshot, kClass),
                        t.previousChildName!!,
                        t.eventType!!
                )
            } else {
                throw Exceptions.propagate(RuntimeException("child dataSnapshot doesn't exist"))
            }
        }
    }

    companion object {

        fun <U: Any> of(kClass: KClass<U>): DataSnapshotMapper<DataSnapshot, U> {
            return TypedDataSnapshotMapper(kClass)
        }

        fun <U: Any> listOf(kClass: KClass<U>): DataSnapshotMapper<DataSnapshot, List<U>> {
            return TypedListDataSnapshotMapper(kClass)
        }

        fun <U: Any> mapOf(kClass: KClass<U>): DataSnapshotMapper<DataSnapshot, LinkedHashMap<String, U>> {
            return TypedMapDataSnapshotMapper(kClass)
        }

        fun <U: Any> of(genericTypeIndicator: GenericTypeIndicator<U>): DataSnapshotMapper<DataSnapshot, U> {
            return GenericTypedDataSnapshotMapper(genericTypeIndicator)
        }

        fun <U: Any> ofChildEvent(kClass: KClass<U>):
                DataSnapshotMapper<RxFirebaseChildEvent<DataSnapshot>, RxFirebaseChildEvent<U>> {
            return ChildEventDataSnapshotMapper<U>(kClass)
        }

        private fun <U: Any> getDataSnapshotTypedValue(dataSnapshot: DataSnapshot, kClass: KClass<U>): U {
            return dataSnapshot.getValue(kClass.java) ?: throw Exceptions.propagate(RxFirebaseDataCastException(
                    "unable to cast firebase data response to " + kClass.simpleName))
        }
    }

}
