package com.example.app01.DB

import com.example.app01.dto.Relation
import com.example.app01.dto.User
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.worker.Worker
import retrofit2.Call
import retrofit2.http.*

interface retrofitAPI {
    // Checking if user types correct id and pw
    @GET("/spring/user/login/{account}/{pw}")
    fun checkValidation(@Path("account") account : String, @Path("pw") pw : String) : Call<Boolean>

    @POST("/spring/user")
    fun createAccount(@Body newUser : User) : Call<Boolean>

    @GET("/spring/user/account/{account}")
    fun getUserInfo(@Path("account") account: String) : Call<User>

    @GET("/spring/user/account/{account}")
    fun searchUserByAccount(@Path("account") account : String) : Call<User>

    @GET("/spring/branch/id_boss/{id_boss}")
    fun searchBranchByIdBoss(@Path("id_boss") id_boss : Int) : Call<List<Branch>>

    @POST("/spring/branch")
    fun createBranch(@Body newBranch : Branch) : Call<Boolean>

    @DELETE("/spring/branch")
    fun deleteBranch(@Body target : Branch) : Call<Boolean>

    @PUT("/spring/branch")
    fun modifyBranch(@Body newBranch : Branch) : Call<Boolean>
}