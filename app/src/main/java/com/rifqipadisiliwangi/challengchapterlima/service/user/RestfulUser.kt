package com.rifqipadisiliwangi.challengchapterlima.service.user

import com.rifqipadisiliwangi.challengchapterlima.model.user.DataUser
import com.rifqipadisiliwangi.challengchapterlima.model.user.ResponUserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestfulUser {

    @GET("User")
    fun getAllUser(): Call<List<ResponUserItem>>

    @POST("User")
    fun postUser(@Body request: DataUser): Call<ResponUserItem>

    @PUT("User/{id}")
    fun updateUser(@Path("id") id: String, @Body request: DataUser) : Call<ResponUserItem>




}