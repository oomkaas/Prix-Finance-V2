package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.SyncData
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class SyncDataCreateDto(val userId: UUID, val syncStatus: String, val dataChanges: String?)
data class SyncDataUpdateDto(val syncStatus: String, val dataChanges: String)

interface SyncDataApiService {

    @GET("api/syncdata")
    fun getSyncData(): Call<List<SyncData>>

    @GET("api/syncdata/{id}")
    fun getSyncData(@Path("id") id: UUID): Call<SyncData>

    @POST("api/syncdata")
    fun createSyncData(@Body syncDataCreateDto: SyncDataCreateDto): Call<SyncData>

    @PUT("api/syncdata/{id}")
    fun updateSyncData(@Path("id") id: UUID, @Body syncDataUpdateDto: SyncDataUpdateDto): Call<Void>

    @DELETE("api/syncdata/{id}")
    fun deleteSyncData(@Path("id") id: UUID): Call<Void>
}
