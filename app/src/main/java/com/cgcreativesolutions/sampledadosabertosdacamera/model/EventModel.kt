package com.cgcreativesolutions.sampledadosabertosdacamera.model

data class EventModel(
    val id: Int,
    val status: EventStatus,
    val type: EventType,
    val description: String,
    val startDate: String,
    val endDate: String?,
    val theme: EventTheme
)

enum class EventStatus {
    CANCELED, FINISHED, SCHEDULED
}

enum class EventTheme {
    HEALTH,
    TECHNOLOGY,
    POLITICS,
    EDUCATION,
    ENERGY,
    ECONOMY,
    TRANSPORT
}

enum class EventType {
    PUBLIC_HEARING,     // Audiência Pública
    DELIBERATIVE_MEETING, // Reunião Deliberativa
    GENERAL_COMMISSION, // Comissão Geral
    ROUNDTABLE,         // Mesa Redonda
    DEBATE              // Debate
}


val mockEvents = listOf(
    // 03/09/2025 - Quarta
    EventModel(
        id = 77575,
        status = EventStatus.CANCELED,
        type = EventType.PUBLIC_HEARING,
        description = "Financiamento da estrutura do SAMU",
        startDate = "2025-09-03T09:00",
        endDate = null,
        theme = EventTheme.HEALTH
    ),
    EventModel(
        id = 78720,
        status = EventStatus.FINISHED,
        type = EventType.PUBLIC_HEARING,
        description = "Desafios e oportunidades dos data centers",
        startDate = "2025-09-03T09:44",
        endDate = "2025-09-03T11:26",
        theme = EventTheme.TECHNOLOGY
    ),
    EventModel(
        id = 78805,
        status = EventStatus.FINISHED,
        type = EventType.DELIBERATIVE_MEETING,
        description = "Discussão e votação de propostas legislativas",
        startDate = "2025-09-03T10:30",
        endDate = "2025-09-03T11:20",
        theme = EventTheme.POLITICS
    ),
    EventModel(
        id = 78883,
        status = EventStatus.FINISHED,
        type = EventType.GENERAL_COMMISSION,
        description = "Debate sobre a proposta de Reforma Administrativa",
        startDate = "2025-09-03T10:00",
        endDate = "2025-09-03T13:33",
        theme = EventTheme.POLITICS
    ),

    // 04/09/2025 - Quinta
    EventModel(
        id = 80001,
        status = EventStatus.FINISHED,
        type = EventType.ROUNDTABLE,
        description = "Discussão da pauta da Comissão de Educação",
        startDate = "2025-09-04T09:00",
        endDate = "2025-09-04T10:15",
        theme = EventTheme.EDUCATION
    ),
    EventModel(
        id = 80002,
        status = EventStatus.SCHEDULED,
        type = EventType.PUBLIC_HEARING,
        description = "Impactos do 5G na infraestrutura nacional",
        startDate = "2025-09-04T10:30",
        endDate = null,
        theme = EventTheme.TECHNOLOGY
    ),
    EventModel(
        id = 80003,
        status = EventStatus.FINISHED,
        type = EventType.DELIBERATIVE_MEETING,
        description = "Votação de propostas legislativas",
        startDate = "2025-09-04T14:00",
        endDate = "2025-09-04T15:45",
        theme = EventTheme.POLITICS
    ),

    // 05/09/2025 - Sexta
    EventModel(
        id = 80101,
        status = EventStatus.FINISHED,
        type = EventType.PUBLIC_HEARING,
        description = "Políticas de incentivo à energia renovável",
        startDate = "2025-09-05T09:30",
        endDate = "2025-09-05T11:00",
        theme = EventTheme.ENERGY
    ),
    EventModel(
        id = 80102,
        status = EventStatus.SCHEDULED,
        type = EventType.DEBATE,
        description = "Regulamentação de inteligência artificial",
        startDate = "2025-09-05T11:30",
        endDate = null,
        theme = EventTheme.TECHNOLOGY
    ),
    EventModel(
        id = 80103,
        status = EventStatus.FINISHED,
        type = EventType.DELIBERATIVE_MEETING,
        description = "Discussão de propostas de orçamento",
        startDate = "2025-09-05T15:00",
        endDate = "2025-09-05T16:20",
        theme = EventTheme.ECONOMY
    ),

    // 08/09/2025 - Segunda
    EventModel(
        id = 80201,
        status = EventStatus.FINISHED,
        type = EventType.PUBLIC_HEARING,
        description = "Sistema de transporte público urbano",
        startDate = "2025-09-08T09:00",
        endDate = "2025-09-08T10:45",
        theme = EventTheme.TRANSPORT
    ),
    EventModel(
        id = 80202,
        status = EventStatus.SCHEDULED,
        type = EventType.ROUNDTABLE,
        description = "Educação digital e ensino híbrido",
        startDate = "2025-09-08T11:00",
        endDate = null,
        theme = EventTheme.EDUCATION
    ),
    EventModel(
        id = 80203,
        status = EventStatus.FINISHED,
        type = EventType.DELIBERATIVE_MEETING,
        description = "Votação de projetos em pauta",
        startDate = "2025-09-08T14:00",
        endDate = "2025-09-08T15:30",
        theme = EventTheme.POLITICS
    ),
    EventModel(
        id = 80204,
        status = EventStatus.FINISHED,
        type = EventType.DEBATE,
        description = "Perspectivas econômicas 2026",
        startDate = "2025-09-08T16:00",
        endDate = "2025-09-08T17:20",
        theme = EventTheme.ECONOMY
    ),

    // 09/09/2025 - Terça
    EventModel(
        id = 80301,
        status = EventStatus.SCHEDULED,
        type = EventType.PUBLIC_HEARING,
        description = "Segurança cibernética no setor público",
        startDate = "2025-09-09T09:00",
        endDate = null,
        theme = EventTheme.TECHNOLOGY
    ),
    EventModel(
        id = 80302,
        status = EventStatus.FINISHED,
        type = EventType.ROUNDTABLE,
        description = "Discussão de projetos de inovação",
        startDate = "2025-09-09T10:30",
        endDate = "2025-09-09T11:50",
        theme = EventTheme.TECHNOLOGY
    ),
    EventModel(
        id = 80303,
        status = EventStatus.FINISHED,
        type = EventType.DELIBERATIVE_MEETING,
        description = "Apreciação de medidas provisórias",
        startDate = "2025-09-09T14:00",
        endDate = "2025-09-09T15:40",
        theme = EventTheme.POLITICS
    )
)



