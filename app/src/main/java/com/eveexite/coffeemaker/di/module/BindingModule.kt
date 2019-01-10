package com.eveexite.coffeemaker.di.module

import androidx.lifecycle.ViewModel
import com.eveexite.coffeemaker.presentation.main.MainViewModel
import com.eveexite.coffeemaker.di.annotations.PerActivity
import com.eveexite.coffeemaker.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Ivan on 29/01/2018.
 */

@Module
abstract class BindingModule {

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}