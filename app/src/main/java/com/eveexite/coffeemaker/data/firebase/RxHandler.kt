package com.eveexite.coffeemaker.data.firebase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task

import io.reactivex.ObservableEmitter

/**
 * Created by ivan on 6/1/17.
 */

class RxHandler<T> private constructor(private val observableEmitter: ObservableEmitter<in T>) : OnSuccessListener<T>, OnFailureListener, OnCompleteListener<T> {

    override fun onSuccess(res: T) {
        observableEmitter.onNext(res)
    }

    override fun onComplete(task: Task<T>) {
        observableEmitter.onComplete()
    }

    override fun onFailure(e: Exception) {
        observableEmitter.onError(e)
    }

    companion object {

        fun <T> assignOnTask(observableEmitter: ObservableEmitter<in T>, task: Task<T>) {
            val handler = RxHandler(observableEmitter)
            task.addOnSuccessListener(handler)
            task.addOnFailureListener(handler)
            try {
                task.addOnCompleteListener(handler)
            } catch (t: Throwable) {
                // ignore
            }

        }
    }

}

