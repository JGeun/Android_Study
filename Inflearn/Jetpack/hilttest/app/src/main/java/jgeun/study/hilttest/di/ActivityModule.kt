package jgeun.study.hilttest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import jgeun.study.hilttest.di.qualifier.ActivityHash

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @ActivityHash
    @Provides
    fun provideHash() = hashCode().toString()

}