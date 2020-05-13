package com.example.myuniversityclient.di

import com.example.myuniversityclient.data.repository.catering.CateringService
import com.example.myuniversityclient.data.repository.catering.CateringServiceMock
import com.example.myuniversityclient.data.repository.electives.ElectivesService
import com.example.myuniversityclient.data.repository.electives.ElectivesServiceMock
import com.example.myuniversityclient.data.repository.http.HttpClientService
import com.example.myuniversityclient.data.repository.itservices.ITLinksService
import com.example.myuniversityclient.data.repository.itservices.ITLinksServiceMock
import com.example.myuniversityclient.data.repository.main.MainService
import com.example.myuniversityclient.data.repository.main.MainServiceMock
import com.example.myuniversityclient.data.repository.main.ProfileService
import com.example.myuniversityclient.data.repository.main.ProfileServiceMock
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideMainService(): MainService {
        return HttpClientService()
    }
    @Provides
    fun providesProfileService(): ProfileService{
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