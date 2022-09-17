package com.som3a.feature.ui.main

import androidx.lifecycle.viewModelScope
import com.som3a.base.BaseViewModel
import com.som3a.common.Resource
import com.som3a.domain.usecase.GetWeatherDetailsListUseCase
import com.som3a.feature.mapper.WeatherDetailsDomainUiMapper
import com.som3a.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherDetailsListUseCase: GetWeatherDetailsListUseCase,
    private val weatherMapper: WeatherDetailsDomainUiMapper
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            viewState = MainContract.WeatherState.Idle,
            selectedWeather = null
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnFetchWeatherList -> {
                fetchList()
            }
            is MainContract.Event.OnSelectWeatherDetails -> {
                event.weatherDetails
            }
        }
    }

    private fun fetchList() =
        viewModelScope.launch {
            getWeatherDetailsListUseCase.execute()
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
                            if (it.data.isNotEmpty())
                                setState {
                                    copy(
                                        viewState = MainContract.WeatherState.WeatherListSuccess(
                                            weatherDetailsList = weatherMapper.mapList(it.data)
                                        )
                                    )
                                }
                        }
                    }
                }
        }

}