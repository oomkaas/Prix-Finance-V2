package com.st10079970.prixfinance.Api.Models

import java.util.*

data class SyncData(
    val syncId: UUID,
    val userId: UUID,
    val syncStatus: String = "",
    val lastSyncDate: Date? = null,
    val dataChanges: String? = null,
    val user: User
)