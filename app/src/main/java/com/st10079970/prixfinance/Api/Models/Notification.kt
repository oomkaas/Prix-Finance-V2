package com.st10079970.prixfinance.Api.Models

import java.util.*

data class Notification(
    val notificationId: UUID,
    val userId: UUID,
    val message: String = "",
    val createdAt: Date? = null,
    val isRead: Boolean,
    val user: User
)