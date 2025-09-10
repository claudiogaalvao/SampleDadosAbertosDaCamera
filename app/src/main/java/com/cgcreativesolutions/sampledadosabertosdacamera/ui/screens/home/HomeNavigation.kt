package com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home

sealed class HomeNavigation {
    data class ToEventDetails(
        val eventId: Int,
        val eventType: String
    ): HomeNavigation()
}