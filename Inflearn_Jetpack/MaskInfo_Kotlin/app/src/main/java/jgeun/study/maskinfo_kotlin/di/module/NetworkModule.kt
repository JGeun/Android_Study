package jgeun.study.maskinfo_kotlin.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jgeun.study.maskinfo_kotlin.repository.MaskService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMaskServices() : MaskService{
        var gson: Gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(MaskService::class.java)
    }
}