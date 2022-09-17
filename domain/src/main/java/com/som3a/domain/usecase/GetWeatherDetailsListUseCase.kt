package com.som3a.domain.usecase

import com.som3a.common.Resource
import com.som3a.domain.entity.WeatherDetailsEntity
import com.som3a.domain.qualifiers.IoDispatcher
import com.som3a.domain.repository.Repository
import com.som3a.domain.usecase.base.FlowUseCaseNoParam
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetWeatherDetailsListUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCaseNoParam<List<WeatherDetailsEntity>>() {
    override suspend fun buildRequest(): Flow<Resource<List<WeatherDetailsEntity>>> {
        return repository.getWeatherDetailsList()
            .flowOn(dispatcher)
    }
}