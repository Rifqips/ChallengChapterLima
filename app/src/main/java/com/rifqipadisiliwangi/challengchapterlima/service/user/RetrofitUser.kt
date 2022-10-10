package com.rifqipadisiliwangi.challengchapterlima.service.user

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUser {
    private const val BASE_URL = "https://6331b379cff0e7bf70f4938d.mockapi.io/"

    val instance : RestfulUser by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfulUser::class.java)
    }
}