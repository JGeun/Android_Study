package jgeun.study.hilttest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jgeun.study.hilttest.di.qualifier.AppHash

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @AppHash
    @Provides
    fun provideHash() = hashCode().toString()
}