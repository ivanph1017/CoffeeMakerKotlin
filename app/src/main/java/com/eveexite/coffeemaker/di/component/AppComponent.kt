package com.eveexite.coffeemaker.di.component

import com.eveexite.coffeemaker.App
import com.eveexite.coffeemaker.di.module.AppModule
import com.eveexite.coffeemaker.di.module.BindingModule
import com.eveexite.coffeemaker.di.module.BuildersModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton



/**
 * Created by Ivan on 29/01/2018.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        BuildersModule::class,
        BindingModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}