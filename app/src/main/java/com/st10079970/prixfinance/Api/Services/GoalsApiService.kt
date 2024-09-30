package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.Goal
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class CreateGoalDto(val userId: UUID, val targetAmount: Double, val deadline: String)
data class UpdateGoalDto(val targetAmount: Double, val progress: Double, val deadline: String)

interface GoalsApiService {

    @GET("api/goals")
    fun getGoals(): Call<List<Goal>>

    @GET("api/goals/{id}")
    fun getGoal(@Path("id") id: UUID): Call<Goal>

    @POST("api/goals")
    fun createGoal(@Body createGoalDto: CreateGoalDto): Call<Goal>

    @PUT("api/goals/{id}")
    fun updateGoal(@Path("id") id: UUID, @Body updateGoalDto: UpdateGoalDto): Call<Void>

    @DELETE("api/goals/{id}")
    fun deleteGoal(@Path("id") id: UUID): Call<Void>
}
