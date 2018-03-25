package com.eveexite.coffeemaker.presentation.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.eveexite.coffeemaker.presentation.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Ivan on 29/01/2018.
 */
abstract class BaseActivity<T : ViewModel> : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass().java)
    }

    protected abstract fun getLayout(): Int

    protected abstract fun getViewModelClass(): KClass<T>

}