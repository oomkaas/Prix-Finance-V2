package com.st10079970.prixfinance.Api.Models

import java.util.*

data class User(
    val userId: UUID,
    val email: String = "",
    val passwordHash: String = "",
    val biometricData: ByteArray? = null,
    val preferredLanguage: String? = null,
    val createdAt: Date? = null,
    val budgets: List<Budget> = emptyList(),
    val gamification: Gamification? = null,
    val goals: List<Goal> = emptyList(),
    val notifications: List<Notification> = emptyList(),
    val securitySetting: SecuritySetting? = null,
    val syncData: List<SyncData> = emptyList(),
    val transactions: List<Transaction> = emptyList()
)