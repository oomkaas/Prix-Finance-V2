package com.st10079970.prixfinance.Api.Services

import com.st10079970.prixfinance.Api.Models.User
import retrofit2.Call
import retrofit2.http.*
import java.util.*

data class UserCreateDto(val email: String, val password: String, val preferredLanguage: String?)
data class UserUpdateDto(val userId: UUID, val email: String?, val preferredLanguage: String?, val password: String?)

interface UsersApiService {

    @GET("api/users")
    fun getUsers(): Call<List<User>>

    @GET("api/users/{id}")
    fun getUser(@Path("id") id: UUID): Call<User>

    @POST("api/users")
    fun createUser(@Body userCreateDto: UserCreateDto): Call<User>

    @PUT("api/users/{id}")
    fun updateUser(@Path("id") id: UUID, @Body userUpdateDto: UserUpdateDto): Call<Void>

    @DELETE("api/users/{id}")
    fun deleteUser(@Path("id") id: UUID): Call<Void>
}