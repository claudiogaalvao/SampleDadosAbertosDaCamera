package com.cgcreativesolutions.sampledadosabertosdacamera.ui.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cgcreativesolutions.sampledadosabertosdacamera.R
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType

@Composable
fun EventType.displayName(): String {
    val resId = when (this) {
        EventType.PUBLIC_HEARING -> R.string.type_public_hearing
        EventType.DELIBERATIVE_MEETING -> R.string.type_deliberative_meeting
        EventType.GENERAL_COMMISSION -> R.string.type_general_commission
        EventType.ROUNDTABLE -> R.string.type_roundtable
        EventType.DEBATE -> R.string.type_debate
    }
    return stringResource(id = resId)
}

@Composable
fun EventType.displayDescription(): String {
    val resId = when (this) {
        EventType.PUBLIC_HEARING -> R.string.desc_public_hearing
        EventType.DELIBERATIVE_MEETING -> R.string.desc_deliberative_meeting
        EventType.GENERAL_COMMISSION -> R.string.desc_general_commission
        EventType.ROUNDTABLE -> R.string.desc_roundtable
        EventType.DEBATE -> R.string.desc_debate
    }
    return stringResource(id = resId)
}