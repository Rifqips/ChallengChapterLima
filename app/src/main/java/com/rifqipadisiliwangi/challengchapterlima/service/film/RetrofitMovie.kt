package com.rifqipadisiliwangi.challengchapterlima.service.film

import com.rifqipadisiliwangi.challengchapterlima.model.film.ResponMovieItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitMovie {

    const val BASE_URL = "https://api.themoviedb.org/"

    val instance : RestfulMovie by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfulMovie::class.java)
    }
}