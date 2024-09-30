package com.st10079970.prixfinance.Api.Models

import java.util.*

data class Gamification(
    val userId: UUID,
    val points: Int,
    val achievements: String? = null,
    val user: User
)