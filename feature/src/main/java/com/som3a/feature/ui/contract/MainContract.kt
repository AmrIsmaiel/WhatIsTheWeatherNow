package com.som3a.feature.ui.contract

import com.som3a.base.UiEffect
import com.som3a.base.UiEvent
import com.som3a.base.UiState
import com.som3a.domain.entity.WeatherRequest
import com.som3a.feature.model.WeatherDetailsUiModel


/**
 * Contract of Main Screen
 */
class MainContract {

    sealed class Event : UiEvent {
        object OnFetchWeatherList : Event()
        data class OnFetchWeatherDetails(val weatherRequest: WeatherRequest) : Event()
        data class OnSelectWeatherDetails(val weatherDetails: WeatherDetailsUiModel) : Event()
    }

    data class State(
        val viewState: WeatherState,
        val selectedWeather: WeatherDetailsUiModel? = null
    ) : UiState

    sealed class WeatherState {
        object Idle : WeatherState()
        object Loading : WeatherState()
        data class WeatherSuccess(val weatherDetails: WeatherDetailsUiModel) : WeatherState()
        data class WeatherListSuccess(val weatherDetailsList: List<WeatherDetailsUiModel>) : WeatherState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}