package com.som3a.data.repository

import com.som3a.common.Resource
import com.som3a.data.mapper.WeatherDataDomainMapper
import com.som3a.domain.entity.WeatherDetailsEntity
import com.som3a.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val weatherDataDomainMapper: WeatherDataDomainMapper
) : Repository {
    override suspend fun getWeatherDetailsList(): Flow<Resource<List<WeatherDetailsEntity>>> {
        return flow {
            try {
                val data = localDataSource.getItems()
                val result = mutableListOf<WeatherDetailsEntity>()
                data.forEach { result.add(weatherDataDomainMapper.map(it)) }
                emit(Resource.Success(result))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
                emit(Resource.Success(emptyList()))
            }
        }
    }

    override suspend fun getWeatherDetails(lat: Double, long: Double): Flow<Resource<WeatherDetailsEntity>> {
        return flow {
            val data = remoteDataSource.getWeatherDetails(lat, long)
            localDataSource.addItem(data)
            emit(Resource.Success(weatherDataDomainMapper.map(data)))
        }
    }
}