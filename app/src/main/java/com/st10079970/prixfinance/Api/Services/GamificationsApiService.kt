package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.Gamification
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class CreateGamificationDto(val userId: UUID, val points: Int, val achievements: String)
data class UpdateGamificationDto(val points: Int, val achievements: String)

interface GamificationsApiService {

    @GET("api/gamifications")
    fun getGamifications(): Call<List<Gamification>>

    @GET("api/gamifications/{id}")
    fun getGamification(@Path("id") id: UUID): Call<Gamification>

    @POST("api/gamifications")
    fun createGamification(@Body createGamificationDto: CreateGamificationDto): Call<Gamification>

    @PUT("api/gamifications/{id}")
    fun updateGamification(@Path("id") id: UUID, @Body updateGamificationDto: UpdateGamificationDto): Call<Void>

    @DELETE("api/gamifications/{id}")
    fun deleteGamification(@Path("id") id: UUID): Call<Void>
}
