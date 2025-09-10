package com.cgcreativesolutions.sampledadosabertosdacamera.ui.app

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class EventDetails(val id: Int, val eventType: String)