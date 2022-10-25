package com.rifqipadisiliwangi.challengchapterlima.viewmodel

import com.rifqipadisiliwangi.challengchapterlima.model.user.ResponUserItem
import com.rifqipadisiliwangi.challengchapterlima.service.user.RestfulUser
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelUserTest{

    lateinit var servis : RestfulUser

    @Before
    fun setUp(){
        servis = mockk()
    }

    @Test
    fun getUserTest(): Unit = runBlocking {
        val respAllUser = mockk<Call<List<ResponUserItem>>>()

        every {
            runBlocking {
                servis.getAllUser()
            }
        }returns respAllUser

        val result = servis.getAllUser()
        verify {
            runBlocking {
                servis.getAllUser()
            }
            Assert.assertEquals(result,respAllUser)
        }
    }

    @Test
    fun testUser(){
        val respAllUser = mockk<Call<List<ResponUserItem>>>()
        every {
            servis.getAllUser()
        }returns respAllUser

        val result = servis.getAllUser()

        verify {
            servis.getAllUser()
        }
        Assert.assertEquals(result,respAllUser)
    }
}