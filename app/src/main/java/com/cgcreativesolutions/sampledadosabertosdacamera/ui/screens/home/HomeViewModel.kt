package com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType
import com.cgcreativesolutions.sampledadosabertosdacamera.model.mockEvents
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.model.FilterUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.emit(
                HomeState(
                    filters = getMockFilters(),
                    events = mockEvents
                )
            )
        }
    }

    private fun getMockFilters(): List<FilterUiModel> {
        return EventType.entries.map { theme ->
            FilterUiModel(
                eventType = theme,
                isSelected = false
            )
        }
    }

    fun onEvent(mainEvents: HomeEvents) {
        when(mainEvents) {
            is HomeEvents.FilterItemSelected -> handleFilterItemSelected(mainEvents.itemSelected)
        }
    }

    private fun handleFilterItemSelected(itemSelected: EventType) {
        viewModelScope.launch {
            _state.update { actualState ->
                val currentSelection = actualState.filters.firstOrNull { it.isSelected }
                if (currentSelection?.eventType == itemSelected) {
                    return@update getInitialState()
                }
                val updatedFilters = actualState.filters.map { filter ->
                    filter.copy(isSelected = filter.eventType == itemSelected)
                }
                val filteredEvents = mockEvents.mapNotNull { event ->
                    if (event.type == itemSelected) {
                        event
                    } else null
                }
                actualState.copy(
                    filters = updatedFilters,
                    events = filteredEvents
                )
            }
        }
    }

    private fun getInitialState(): HomeState {
        return HomeState(
            filters = getMockFilters(),
            events = mockEvents
        )
    }

}