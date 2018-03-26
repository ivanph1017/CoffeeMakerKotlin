package com.eveexite.coffeemaker.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.eveexite.coffeemaker.di.annotations.PerActivity
import com.eveexite.coffeemaker.domain.usecase.CheckTurnOn
import com.eveexite.coffeemaker.domain.usecase.GetCoffeeMaker
import com.eveexite.coffeemaker.domain.usecase.TurnOnCoffeeMaker
import com.eveexite.coffeemaker.presentation.main.mapper.CoffeeMakerUiMapper
import com.eveexite.coffeemaker.presentation.main.model.AnimUi
import com.eveexite.coffeemaker.presentation.main.model.InfoUi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ivan on 29/01/2018.
 *
 */

@PerActivity
class MainViewModel @Inject constructor(private val getCoffeeMaker: GetCoffeeMaker,
                                        private val checkTurnOn: CheckTurnOn,
                                        private val turnOnCoffeeMaker: TurnOnCoffeeMaker,
                                        private val mapper: CoffeeMakerUiMapper): ViewModel() {

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
        cd.add(getCoffeeMaker.execute().map { t -> mapper.reverseMap(t) }
                .subscribe({ coffeeMakerUi ->
                    // Ui Changes
                    infoLive.postValue(coffeeMakerUi.infoUi)
                    statusTextLogic(coffeeMakerUi.statusUi.text)
                    animLogic(coffeeMakerUi.animUi)
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
        var pair: Pair<String, String>
        cd.add(checkTurnOn.execute().subscribe({ msg ->
            pair = Pair("Aviso", msg)
            msgConfirmLive.postValue(pair)
        }, { throwable ->
            Log.e("Aviso", throwable.message!!)
            pair = Pair("Aviso", throwable.message!!)
            msgLive.postValue(pair)
        }))
    }

    fun checkCoffeeMakerStatus() {
        cd.add(turnOnCoffeeMaker.execute().subscribe({ _ ->  }, { throwable ->
            Log.e("Error", throwable.message!!)
            val pair: Pair<String, String> = Pair("Error", throwable.message!!)
            msgLive.postValue(pair)
        }))
    }

    fun onDestroy() {
        cd.dispose()
    }

}