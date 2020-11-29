package com.example.app01.DB

import com.example.app01.dto.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface retrofitAPI {
    // Checking if user types correct id and pw
    @GET("/spring/user/login/{account}/{pw}")
    fun checkValidation(@Path("account") account : String, @Path("pw") pw : String) : Call<Boolean>

    @POST("/spring/user")
    fun createAccount(@Body newUser : User) : Call<Boolean>

}