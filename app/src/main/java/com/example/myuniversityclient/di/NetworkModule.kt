package com.example.myuniversityclient.di

import com.example.myuniversityclient.data.repository.main.ITLinksService
import com.example.myuniversityclient.data.repository.main.ITLinksServiceMock
import com.example.myuniversityclient.data.repository.main.MainService
import com.example.myuniversityclient.data.repository.main.MainServiceMock
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideMainService(): MainService {
        return MainServiceMock()
    }

    @Provides
    fun provideITLinksService(): ITLinksService {
        return ITLinksServiceMock()
    }
}