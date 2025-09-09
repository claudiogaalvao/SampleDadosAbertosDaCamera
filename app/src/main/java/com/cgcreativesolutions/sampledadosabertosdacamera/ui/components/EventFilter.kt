package com.cgcreativesolutions.sampledadosabertosdacamera.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.mapper.displayName
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.model.FilterUiModel

@Composable
fun EventFilter(
    filters: List<FilterUiModel>,
    onSelect: (theme: EventType) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        filters.map { filter ->
            val containerColor = if (filter.isSelected) {
                Color.Green
            } else {
                Color.DarkGray
            }
            val textColor = if (filter.isSelected) {
                Color.Black
            } else {
                Color.White
            }
            Text(
                modifier = Modifier
                    .background(
                        color = containerColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
                    .clickable(
                        enabled = true,
                        onClick = { onSelect(filter.eventType) }
                    ),
                text = filter.eventType.displayName().uppercase(),
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}