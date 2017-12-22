package com.feliperoriz.watchdog.di

import com.feliperoriz.watchdog.data.MemberTypeAdapter
import com.feliperoriz.watchdog.network.CongressApi
import com.feliperoriz.watchdog.network.CongressApiInterceptor
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by feliperoriz on 12/21/17.
 */
@Module
class NetworkModule {

    private val baseUrl = "https://api.propublica.org/congress/v1/"

    @Singleton
    @Provides
    fun providesMemberTypeAdapter() = MemberTypeAdapter()

    @Singleton
    @Provides
    fun providesMoshi(memberAdapter: MemberTypeAdapter): Moshi {
        return Moshi.Builder()
                .add(memberAdapter)
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    @Singleton
    @Provides
    fun providesInterceptor(): CongressApiInterceptor {
        return CongressApiInterceptor()
    }

    @Singleton
    @Provides
    fun providesClient(interceptor: CongressApiInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun providesCongressApi(retrofit: Retrofit): CongressApi {
        return retrofit.create(CongressApi::class.java)
    }
}