package com.example.myuniversityclient.di

import android.content.Context
import com.example.myuniversityclient.MainApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplication() = MainApplication.APPLICATION

    @Provides
    fun provideContext(): Context = MainApplication.APPLICATION

}