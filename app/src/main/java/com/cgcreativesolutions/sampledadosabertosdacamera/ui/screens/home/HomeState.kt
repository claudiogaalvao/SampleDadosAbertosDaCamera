package com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home

import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventModel
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.model.FilterUiModel

data class HomeState(
    val filters: List<FilterUiModel> = emptyList(),
    val events: List<EventModel> = emptyList()
)