package com.rifqipadisiliwangi.challengchapterlima.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rifqipadisiliwangi.challengchapterlima.model.film.ResponMovieItem
import com.rifqipadisiliwangi.challengchapterlima.model.user.DataUser
import com.rifqipadisiliwangi.challengchapterlima.model.user.ResponUserItem
import com.rifqipadisiliwangi.challengchapterlima.service.film.RetrofitMovie
import com.rifqipadisiliwangi.challengchapterlima.service.user.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser: ViewModel() {

    var liveDataMovie: MutableLiveData<ResponMovieItem>
    var liveDataUser: MutableLiveData<ResponUserItem> = MutableLiveData()
    var updateUser : MutableLiveData<ResponUserItem> = MutableLiveData()
    var loading = MutableLiveData<Boolean>()
    var postLDUser: MutableLiveData<ResponUserItem> = MutableLiveData()

    init {
        updateUser = MutableLiveData()
        liveDataUser = MutableLiveData()
        liveDataMovie = MutableLiveData()
        loading = MutableLiveData()
        callApiMovie()

    }

    fun getMovie():MutableLiveData<ResponMovieItem>{
        return liveDataMovie
    }

    fun updateUser():MutableLiveData<ResponUserItem>{
        return updateUser
    }

    fun postLiveDataUser():MutableLiveData<ResponUserItem>{
        return liveDataUser
    }

    fun callApiMovie(){
        RetrofitMovie.instance.getAllMovie()
            .enqueue(object : Callback<ResponMovieItem>{
                override fun onResponse(
                    call: Call<ResponMovieItem>,
                    response: Response<ResponMovieItem>
                ) {
                    if (response.isSuccessful){
                        liveDataMovie.postValue(response.body())
                        Log.d("data",response.body()?.results.toString())
                    }else{
                        Log.d("data",response.body()?.results.toString())
                    }
                }

                override fun onFailure(call: Call<ResponMovieItem>, t: Throwable) {
                    Log.d("data",call.toString())
                }

            })

    }

    fun callPostUser (username: String, email: String, password: String){
        RetrofitUser.instance.postUser(DataUser(username,email, password))
            .enqueue(object : Callback<ResponUserItem>{
                override fun onResponse(
                    call: Call<ResponUserItem>,
                    response: Response<ResponUserItem>
                ) {
                    if (response.isSuccessful){
                        liveDataUser.postValue(response.body())
                    }else{
                        Log.d("data", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<ResponUserItem>, t: Throwable) {
                    Log.d("data", call.toString())
                }

            })
    }

    fun updateCallApiUser(id: String, username: String, email: String, password: String){
        RetrofitUser.instance.updateUser(id.toString(), DataUser(username, email, password))
            .enqueue(object  : Callback<ResponUserItem>{
                override fun onResponse(
                    call: Call<ResponUserItem>,
                    response: Response<ResponUserItem>
                ) {
                    if (response.isSuccessful){
                        updateUser.postValue(response.body())
                        Log.d("TAG","onResponse: Berhasil")
                        Log.d("TAG","onResponse: ${response.body()}")
                    }else{
                        Log.d("TAG","onResponse : Gagal")
                        Log.d("data", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<ResponUserItem>, t: Throwable) {
                    Log.d("data", call.toString())
                }

            })
    }
}