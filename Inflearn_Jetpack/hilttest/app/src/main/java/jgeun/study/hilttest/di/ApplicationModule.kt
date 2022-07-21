package jgeun.study.hilttest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jgeun.study.hilttest.di.qualifier.AppHash

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @AppHash
    @Provides
    fun provideHash() = hashCode().toString()
}