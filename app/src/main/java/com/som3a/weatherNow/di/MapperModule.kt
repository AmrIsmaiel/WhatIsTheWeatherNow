package com.som3a.weatherNow.di

import com.som3a.common.Mapper
import com.som3a.data.mapper.WeatherDataDomainMapper
import com.som3a.data.model.WeatherDetailsDTO
import com.som3a.domain.entity.WeatherDetailsEntity
import com.som3a.feature.mapper.WeatherDetailsDomainUiMapper
import com.som3a.feature.model.WeatherDetailsUiModel
import com.som3a.local.mapper.WeatherDetailsLocalDataMapper
import com.som3a.local.model.WeatherDetailsLocal
import com.som3a.remote.mapper.WeatherDetailsRemoteMapper
import com.som3a.remote.model.WeatherDetailsRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsWeatherRemoteMapper(mapper: WeatherDetailsRemoteMapper): Mapper<WeatherDetailsRemote, WeatherDetailsDTO>

    @Binds
    abstract fun bindsWeatherDetailsLocalMapper(mapper: WeatherDetailsLocalDataMapper): Mapper<WeatherDetailsLocal, WeatherDetailsDTO>

    @Binds
    abstract fun bindsWeatherDataDomainMapper(mapper: WeatherDataDomainMapper): Mapper<WeatherDetailsDTO, WeatherDetailsEntity>

    @Binds
    abstract fun bindsWeatherDetailsUiMapper(mapper: WeatherDetailsDomainUiMapper): Mapper<WeatherDetailsEntity, WeatherDetailsUiModel>

}