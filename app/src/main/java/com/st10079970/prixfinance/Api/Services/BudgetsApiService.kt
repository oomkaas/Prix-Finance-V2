package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.Budget
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class CreateBudgetDto(val userId: UUID, val category: String?, val amount: Double, val period: String?)
data class UpdateBudgetDto(val amount: Double, val category: String, val period: String)

interface BudgetsApiService {

    @GET("api/budgets")
    fun getBudgets(): Call<List<Budget>>

    @GET("api/budgets/{id}")
    fun getBudget(@Path("id") id: UUID): Call<Budget>

    @POST("api/budgets")
    fun createBudget(@Body createBudgetDto: CreateBudgetDto): Call<Budget>

    @PUT("api/budgets/{id}")
    fun updateBudget(@Path("id") id: UUID, @Body updateBudgetDto: UpdateBudgetDto): Call<Void>

    @DELETE("api/budgets/{id}")
    fun deleteBudget(@Path("id") id: UUID): Call<Void>
}
