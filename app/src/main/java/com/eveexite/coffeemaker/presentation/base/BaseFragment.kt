package com.eveexite.coffeemaker.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eveexite.coffeemaker.presentation.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Ivan on 29/01/2018.
 */
abstract class BaseFragment<T : ViewModel> : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass().java)
    }

    protected abstract fun getLayout(): Int

    protected abstract fun getViewModelClass(): KClass<T>

}