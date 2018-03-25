package com.eveexite.coffeemaker.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.eveexite.coffeemaker.di.annotations.PerActivity
import com.eveexite.coffeemaker.domain.usecase.GetCoffeeMaker
import com.eveexite.coffeemaker.domain.usecase.TurnOnCoffeeMaker
import com.eveexite.coffeemaker.presentation.main.mapper.CoffeeMakerUiMapper
import com.eveexite.coffeemaker.presentation.main.model.*
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ivan on 29/01/2018.
 *
 */

@PerActivity
class MainViewModel @Inject constructor(private val getCoffeeMaker: GetCoffeeMaker,
                                        private val turnOnCoffeeMaker: TurnOnCoffeeMaker,
                                        private val mapper: CoffeeMakerUiMapper): ViewModel() {

    private var coffeeMakerLive = MutableLiveData<CoffeeMakerUi>()
    private var infoLive = MutableLiveData<InfoUi>()
    private var statusTextLive = MutableLiveData<String>()
    private var animLive = MutableLiveData<AnimUi>()
    private var msgLive = MutableLiveData<Pair<String, String>>()
    private var msgConfirmLive = MutableLiveData<Pair<String, String>>()
    private var msgFinishLive = MutableLiveData<Pair<String, String>>()
    private val cd: CompositeDisposable = CompositeDisposable()

    fun getInfoLive(): LiveData<InfoUi> {
        return infoLive
    }

    fun getStatusTextLive(): LiveData<String> {
        return statusTextLive
    }

    fun getAnimLive(): LiveData<AnimUi> {
        return animLive
    }

    fun getMsgLive(): LiveData<Pair<String, String>> {
        return msgLive
    }

    fun getMsgConfirmLive(): LiveData<Pair<String, String>> {
        return msgConfirmLive
    }

    fun getMsgFinishLive(): LiveData<Pair<String, String>> {
        return msgFinishLive
    }

    fun loadCoffeeMaker() {
        cd.add(getCoffeeMaker.get().map { t -> mapper.reverseMap(t) }
                .subscribe({ coffeeMakerUi ->
                    // Just for storing info here
                    coffeeMakerLive.postValue(coffeeMakerUi)
                    // Ui Changes
                    infoLive.postValue(coffeeMakerUi.info)
                    statusTextLogic(coffeeMakerUi.statusText)
                    animLogic(coffeeMakerUi.anim)
                }, { throwable ->
                    Log.e("Error", throwable.message!!)
                    msgFinishLive.postValue(Pair("Error fatal", throwable.message!!
                            + "\n\nEl app se va a cerrar."))
                })
        )
    }

    private fun statusTextLogic(statusText: String) {
        if (statusTextLive.value == null) {
            statusTextLive.postValue(statusText)
        } else if (!statusTextLive.value!!.equals(statusText, true)) {
            statusTextLive.postValue(statusText)
        }
    }

    private fun animLogic(anim: AnimUi) {
        if (animLive.value == null) {
            animLive.postValue(anim)
        } else if (!animLive.value!!.fileUri.equals(anim.fileUri, true)
                || animLive.value!!.canPlayAnim != anim.canPlayAnim) {
            animLive.postValue(anim)
        }
    }

    fun checkAction() {
        val pair: Pair<String, String>
        if (infoLive.value == null) {
            pair = Pair("Aviso", StatusTextUi.COFFEE_MAKER_UNPLUGGED.text)
            msgLive.postValue(pair)
        } else {
            pair = Pair("Aviso", if (coffeeMakerLive.value!!.turnOn) "Deseas apagar la cafetera?"
            else "Deseas encender la cafetera?")
            msgConfirmLive.postValue(pair)
        }
    }

    fun checkCoffeeMakerStatus() {
        val pair: Pair<String, String>
        when (coffeeMakerLive.value!!.statusCodeUi) {
            StatusCodeUi.COFFEE_MAKER_READY -> {
                turnOnCoffeeMaker.turnOn(true)
            }
            StatusCodeUi.COFFEE_MAKER_UNPLUGGED,
            StatusCodeUi.NOT_ENOUGH_WATER,
            StatusCodeUi.COFFEE_MAKER_RESTING -> {
                pair = Pair("Error", coffeeMakerLive.value!!.statusText)
                msgLive.postValue(pair)
            }
            StatusCodeUi.COFFEE_READY, StatusCodeUi.PREPARING_COFFEE -> {
                turnOnCoffeeMaker.turnOn(false)
            }
        }
    }

    fun onDestroy() {
        cd.dispose()
    }

}