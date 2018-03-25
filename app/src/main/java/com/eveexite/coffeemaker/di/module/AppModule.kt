package com.eveexite.coffeemaker.di.module

import android.content.Context
import com.eveexite.coffeemaker.App
import dagger.Module
import dagger.Provides

/**
 * Created by Ivan on 29/01/2018.
 */

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app

}