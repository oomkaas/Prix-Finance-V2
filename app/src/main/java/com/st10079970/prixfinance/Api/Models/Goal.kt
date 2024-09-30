package com.st10079970.prixfinance.Api.Models

import java.util.*

data class Goal(
    val goalId: UUID,
    val userId: UUID,
    val targetAmount: Double,
    val progress: Double,
    val deadline: Date,
    val createdAt: Date? = null,
    val user: User
)