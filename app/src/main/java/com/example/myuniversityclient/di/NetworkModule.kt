package com.example.myuniversityclient.di

import com.example.myuniversityclient.data.repository.catering.CateringService
import com.example.myuniversityclient.data.repository.catering.CateringServiceHttp
import com.example.myuniversityclient.data.repository.electives.ElectivesService
import com.example.myuniversityclient.data.repository.electives.ElectivesServiceHttp
import com.example.myuniversityclient.data.repository.http.HttpClientService
import com.example.myuniversityclient.data.repository.itservices.ITLinksService
import com.example.myuniversityclient.data.repository.itservices.ITLinksServiceHttp
import com.example.myuniversityclient.data.repository.main.MainService
import com.example.myuniversityclient.data.repository.main.MainServiceHttp
import com.example.myuniversityclient.data.repository.profile.ProfileService
import com.example.myuniversityclient.data.repository.profile.ProfileServiceHttp
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
        return ProfileServiceHttp(httpService)
    }

    @Provides
    fun provideCateringService(): CateringService {
        return CateringServiceHttp(httpService)
    }

    @Provides
    fun provideITLinksService(): ITLinksService {
        return ITLinksServiceHttp(httpService)
    }

    @Provides
    fun provideElectivesService(): ElectivesService {
        return ElectivesServiceHttp(httpService)
    }
}