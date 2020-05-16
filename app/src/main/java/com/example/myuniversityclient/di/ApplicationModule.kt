package com.example.myuniversityclient.di

import android.content.Context
import android.content.SharedPreferences
import com.example.myuniversityclient.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    fun provideApplication() = MainApplication.APPLICATION

    @Provides
    fun provideContext(): Context = MainApplication.APPLICATION

//    @Provides
//    @Singleton
//    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences? {
//        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
//    }

}