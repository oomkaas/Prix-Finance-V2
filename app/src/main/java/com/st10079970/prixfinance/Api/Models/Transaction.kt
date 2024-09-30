package com.st10079970.prixfinance.Api.Models

import java.util.*

data class Transaction(
    val transactionId: UUID,
    val userId: UUID,
    val amount: Double,
    val date: Date,
    val category: String = "",
    val description: String? = null,
    val user: User
)