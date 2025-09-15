package io.github.vlodo_o.fitnessapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.vlodo_o.fitnessapp.BuildConfig
import io.github.vlodo_o.fitnessapp.data.network.NetworkClient
import io.github.vlodo_o.fitnessapp.data.network.RetrofitNetworkClient
import io.github.vlodo_o.fitnessapp.data.network.WorkoutApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Gson> { GsonBuilder().create() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<WorkoutApi> { get<Retrofit>().create(WorkoutApi::class.java) }

    single<NetworkClient> { RetrofitNetworkClient(get()) }

    single(named("videoBaseUrl")) { BuildConfig.API_BASE_URL.dropLast(1) }
}