package com.rifqipadisiliwangi.challengchapterlima.model.user


import com.google.gson.annotations.SerializedName

data class ResponUserItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)