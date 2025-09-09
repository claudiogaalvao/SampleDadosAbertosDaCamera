package com.cgcreativesolutions.sampledadosabertosdacamera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventModel
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType
import com.cgcreativesolutions.sampledadosabertosdacamera.model.mockEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FilterUiModel(
    val eventType: EventType,
    var isSelected: Boolean
)

data class MainState(
    val filters: List<FilterUiModel> = emptyList(),
    val events: List<EventModel> = emptyList()
)

sealed class MainEvents {
    data class FilterItemSelected(val itemSelected: EventType): MainEvents()
}

class MainViewModel: ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.emit(
                MainState(
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

    fun onEvent(mainEvents: MainEvents) {
        when(mainEvents) {
            is MainEvents.FilterItemSelected -> handleFilterItemSelected(mainEvents.itemSelected)
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

    private fun getInitialState(): MainState {
        return MainState(
            filters = getMockFilters(),
            events = mockEvents
        )
    }

}