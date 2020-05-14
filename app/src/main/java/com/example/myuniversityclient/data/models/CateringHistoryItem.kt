package com.example.myuniversityclient.data.models

import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class CateringHistoryItem(
    val start: LocalDate,
    val end: LocalDate,
    val included_items: List<String>,
    val days: Int,
    val price: BigDecimal
)