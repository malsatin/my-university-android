package com.example.myuniversityclient.di

import com.example.myuniversityclient.data.repository.catering.CateringService
import com.example.myuniversityclient.data.repository.catering.CateringServiceMock
import com.example.myuniversityclient.data.repository.electives.ElectivesService
import com.example.myuniversityclient.data.repository.electives.ElectivesServiceMock
import com.example.myuniversityclient.data.repository.http.HttpClientService
import com.example.myuniversityclient.data.repository.itservices.ITLinksService
import com.example.myuniversityclient.data.repository.itservices.ITLinksServiceMock
import com.example.myuniversityclient.data.repository.main.MainService
import com.example.myuniversityclient.data.repository.main.MainServiceHttp
import com.example.myuniversityclient.data.repository.profile.ProfileService
import com.example.myuniversityclient.data.repository.profile.ProfileServiceMock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    var httpService: HttpClientService = HttpClientService()

    @Provides
    @Singleton
    fun provideHttpClientService(): HttpClientService {
        return httpService
    }

    @Provides
    fun provideMainService(): MainService {
        return MainServiceHttp(httpService)
    }

    @Provides
    fun providesProfileService(): ProfileService {
        return ProfileServiceMock()
    }

    @Provides
    fun provideCateringService(): CateringService {
        return CateringServiceMock()
    }

    @Provides
    fun provideITLinksService(): ITLinksService {
        return ITLinksServiceMock()
    }

    @Provides
    fun provideElectivesService(): ElectivesService {
        return ElectivesServiceMock()
    }
}