package com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType
import com.cgcreativesolutions.sampledadosabertosdacamera.model.mockEvents
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.components.EventCard
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.components.EventFilter
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.mapper.displayDescription
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.mapper.displayName
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.model.FilterUiModel
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.theme.SampleDadosAbertosDaCameraTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onEvent: (event: HomeEvents) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                showBottomSheet = false
            }
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "O que significa cada evento",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
                EventType.entries
                    .mapIndexed { index, type ->
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = type.displayName(),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = type.displayDescription()
                            )
                            if (index < EventType.entries.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
        }
    }

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            EventFilter(
                filters = state.filters,
                onSelect = { itemSelected ->
                    onEvent(HomeEvents.FilterItemSelected(itemSelected))
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Eventos do dia",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Icon(
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            showBottomSheet = true
                        }
                    ),
                    imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                    contentDescription = "Help"
                )
            }
        }
        items(
            items = state.events
        ) { event ->
            EventCard(
                modifier = Modifier.padding(horizontal = 12.dp),
                event = event
            )
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

private val filtersMock = EventType.entries.map { theme ->
    FilterUiModel(
        eventType = theme,
        isSelected = false
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    SampleDadosAbertosDaCameraTheme {
        HomeScreen(
            state = HomeState(
                filters = filtersMock,
                events = mockEvents,
            ),
            onEvent = {}
        )
    }
}
