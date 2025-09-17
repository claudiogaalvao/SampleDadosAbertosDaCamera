package com.cgcreativesolutions.sampledadosabertosdacamera.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventModel
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.mapper.displayName

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    event: EventModel,
    onClick: (eventName: String) -> Unit
) {
    val eventName = event.type.displayName()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = true, onClick = { onClick(eventName) }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant, // fundo do card
            contentColor = MaterialTheme.colorScheme.onSurface  // cor padrão dos textos
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Text(
                text = eventName,
                fontWeight = FontWeight.Bold
            )
            Text(text = event.description)
        }
    }
}