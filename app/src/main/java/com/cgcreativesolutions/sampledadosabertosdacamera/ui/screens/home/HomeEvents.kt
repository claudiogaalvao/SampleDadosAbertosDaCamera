package com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home

import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType

sealed class HomeEvents {
    data class FilterItemSelected(val itemSelected: EventType): HomeEvents()
}
