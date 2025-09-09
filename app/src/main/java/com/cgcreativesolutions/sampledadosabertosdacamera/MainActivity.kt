package com.cgcreativesolutions.sampledadosabertosdacamera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventModel
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventTheme
import com.cgcreativesolutions.sampledadosabertosdacamera.model.EventType
import com.cgcreativesolutions.sampledadosabertosdacamera.model.mockEvents
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.theme.SampleDadosAbertosDaCameraTheme

class MainActivity : ComponentActivity() {
    
    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleDadosAbertosDaCameraTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                ) { innerPadding ->
                    val state by viewModel.state.collectAsState()
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onEvent = { event -> viewModel.onEvent(event) }
                    )
                }
            }
        }
    }
}

fun mockEventTypes(): List<FilterUiModel> {
    return EventType.entries.map { theme ->
        FilterUiModel(
            eventType = theme,
            isSelected = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainState,
    onEvent: (event: MainEvents) -> Unit
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
        modifier = Modifier
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
                    onEvent(MainEvents.FilterItemSelected(itemSelected))
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

private val filtersMock = EventType.entries.map { theme ->
    FilterUiModel(
        eventType = theme,
        isSelected = false
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SampleDadosAbertosDaCameraTheme {
        MainScreen(
            state = MainState(
                filters = filtersMock,
                events = mockEvents,
            ),
            onEvent = {}
        )
    }
}