package com.cgcreativesolutions.sampledadosabertosdacamera.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventModel
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.mapper.displayName

@Composable
fun EventCard(event: EventModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Text(
                text = event.type.displayName(),
                fontWeight = FontWeight.Bold
            )
            Text(text = event.description)
        }
    }
}