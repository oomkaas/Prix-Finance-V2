package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.SecuritySetting
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class SecuritySettingCreateDto(val encryptionKey: String)
data class SecuritySettingUpdateDto(val encryptionKey: String)

interface SecuritySettingsApiService {

    @GET("api/securitysettings")
    fun getSecuritySettings(): Call<List<SecuritySetting>>

    @GET("api/securitysettings/{id}")
    fun getSecuritySetting(@Path("id") id: UUID): Call<SecuritySetting>

    @POST("api/securitysettings")
    fun createSecuritySetting(@Body securitySettingCreateDto: SecuritySettingCreateDto): Call<SecuritySetting>

    @PUT("api/securitysettings/{id}")
    fun updateSecuritySetting(@Path("id") id: UUID, @Body securitySettingUpdateDto: SecuritySettingUpdateDto): Call<Void>

    @DELETE("api/securitysettings/{id}")
    fun deleteSecuritySetting(@Path("id") id: UUID): Call<Void>
}
