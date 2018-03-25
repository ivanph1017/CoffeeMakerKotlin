package com.eveexite.coffeemaker.data.firebase

/**
 * Created by ivan on 6/1/17.
 */

class RxFirebaseChildEvent<T> {

    var eventType: EventType? = null
        private set
    var key: String? = null
        private set
    var value: T? = null
        private set
    var previousChildName: String? = null
        private set

    constructor(key: String,
                value: T,
                previousChildName: String,
                eventType: EventType) {
        this.key = key
        this.value = value
        this.previousChildName = previousChildName
        this.eventType = eventType
    }

    constructor(key: String, data: T, eventType: EventType) {
        this.key = key
        this.value = data
        this.eventType = eventType
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as RxFirebaseChildEvent<*>?

        if (eventType != that!!.eventType) return false
        if (if (value != null) value != that.value else that.value != null) return false
        return if (previousChildName != null) previousChildName == that.previousChildName else that.previousChildName == null

    }

    override fun hashCode(): Int {
        var result = if (eventType != null) eventType!!.hashCode() else 0
        result = 31 * result + if (value != null) value!!.hashCode() else 0
        result = 31 * result + (previousChildName?.hashCode() ?: 0)
        return result
    }

    enum class EventType {
        ADDED,
        CHANGED,
        REMOVED,
        MOVED
    }

}
