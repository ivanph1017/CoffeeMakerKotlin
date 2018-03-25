package com.eveexite.coffeemaker.di.module

import com.eveexite.coffeemaker.presentation.main.MainActivity
import com.eveexite.coffeemaker.di.annotations.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ivan on 29/01/2018.
 */

@Module
abstract class BuildersModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

}