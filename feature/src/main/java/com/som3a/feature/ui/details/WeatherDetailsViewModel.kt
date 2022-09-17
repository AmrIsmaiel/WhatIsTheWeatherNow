package com.som3a.feature.ui.details

import androidx.lifecycle.viewModelScope
import com.som3a.base.BaseViewModel
import com.som3a.common.Resource
import com.som3a.domain.entity.WeatherRequest
import com.som3a.domain.usecase.GetWeatherDetailsUseCase
import com.som3a.feature.mapper.WeatherDetailsDomainUiMapper
import com.som3a.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase,
    private val weatherDetailsDomainUiMapper: WeatherDetailsDomainUiMapper
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            viewState = MainContract.WeatherState.Idle,
            selectedWeather = null
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnFetchWeatherDetails -> {
                fetchDetails(event.weatherRequest)
            }
        }
    }

    private fun fetchDetails(weatherRequest: WeatherRequest) =
        viewModelScope.launch {
            getWeatherDetailsUseCase.execute(
                weatherRequest
            )
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(viewState = MainContract.WeatherState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(viewState = MainContract.WeatherState.Idle) }
                        }
                        is Resource.Error -> {
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                        is Resource.Success -> {
                            setState {
                                copy(
                                    viewState = MainContract.WeatherState.WeatherSuccess(
                                        weatherDetails = weatherDetailsDomainUiMapper.map(it.data)
                                    )
                                )
                            }
                        }
                    }
                }
        }
}