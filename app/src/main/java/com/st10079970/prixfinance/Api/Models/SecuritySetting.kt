package com.st10079970.prixfinance.Api.Models

import java.util.*

data class SecuritySetting(
    val userId: UUID,
    val encryptionKey: String = "",
    val lastUpdated: Date? = null,
    val user: User
)