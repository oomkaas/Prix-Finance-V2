package com.st10079970.prixfinance.Api.Models

import java.util.*

data class Budget(
    val budgetId: UUID,
    val userId: UUID,
    val category: String = "",
    val amount: Double,
    val period: String = "",
    val createdAt: Date? = null,
    val user: User
)