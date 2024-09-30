package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.Transaction
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class TransactionCreateDto(val userId: UUID, val amount: Double, val category: String, val description: String?)
data class TransactionUpdateDto(val amount: Double, val category: String, val description: String)

interface TransactionsApiService {

    @GET("api/transactions")
    fun getTransactions(): Call<List<Transaction>>

    @GET("api/transactions/{id}")
    fun getTransaction(@Path("id") id: UUID): Call<Transaction>

    @POST("api/transactions")
    fun createTransaction(@Body transactionCreateDto: TransactionCreateDto): Call<Transaction>

    @PUT("api/transactions/{id}")
    fun updateTransaction(@Path("id") id: UUID, @Body transactionUpdateDto: TransactionUpdateDto): Call<Void>

    @DELETE("api/transactions/{id}")
    fun deleteTransaction(@Path("id") id: UUID): Call<Void>
}
