package io.github.vlodo_o.fitnessapp

import android.app.Application
import io.github.vlodo_o.fitnessapp.di.networkModule
import io.github.vlodo_o.fitnessapp.di.repositoryModule
import io.github.vlodo_o.fitnessapp.di.useCaseModule
import io.github.vlodo_o.fitnessapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FitnessApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FitnessApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}