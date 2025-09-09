package com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.model

import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType

data class FilterUiModel(
    val eventType: EventType,
    var isSelected: Boolean
)