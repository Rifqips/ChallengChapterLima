package com.rifqipadisiliwangi.challengchapterlima.model.user

import java.io.Serializable

data class DataUser(
    val username: String,
    val email: String,
    val password: String,
): Serializable
