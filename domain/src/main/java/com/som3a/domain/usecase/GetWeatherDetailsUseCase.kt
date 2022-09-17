package com.som3a.domain.usecase

import com.som3a.common.Resource
import com.som3a.domain.entity.WeatherDetailsEntity
import com.som3a.domain.entity.WeatherRequest
import com.som3a.domain.qualifiers.IoDispatcher
import com.som3a.domain.repository.Repository
import com.som3a.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<WeatherDetailsEntity, WeatherRequest>() {

    override suspend fun buildRequest(params: WeatherRequest?): Flow<Resource<WeatherDetailsEntity>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(Exception("Location can't be null")))
            }.flowOn(dispatcher)
        }
        return repository.getWeatherDetails(params.lat, params.long)
            .flowOn(dispatcher)
    }
}