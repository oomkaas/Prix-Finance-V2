package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.Notification
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class NotificationCreateDto(val userId: UUID, val message: String)
data class NotificationUpdateDto(val message: String, val isRead: Boolean)

interface NotificationsApiService {

    @GET("api/notifications")
    fun getNotifications(): Call<List<Notification>>

    @GET("api/notifications/{id}")
    fun getNotification(@Path("id") id: UUID): Call<Notification>

    @POST("api/notifications")
    fun createNotification(@Body notificationCreateDto: NotificationCreateDto): Call<Notification>

    @PUT("api/notifications/{id}")
    fun updateNotification(@Path("id") id: UUID, @Body notificationUpdateDto: NotificationUpdateDto): Call<Void>

    @DELETE("api/notifications/{id}")
    fun deleteNotification(@Path("id") id: UUID): Call<Void>
}
