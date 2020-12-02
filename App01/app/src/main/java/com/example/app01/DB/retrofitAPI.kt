package com.example.app01.DB

import com.example.app01.dto.User
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.Relation
import com.example.app01.dto.worker.WorkerInfo
import com.example.app01.dto.worker.WorkerView
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

    @GET("/spring/branch/id_branch")
    fun getIdBranch(@Body target : Branch) : Call<Int>

    @POST("/spring/branch")
    fun createBranch(@Body newBranch : Branch) : Call<Boolean>

    @DELETE("/spring/branch")
    fun deleteBranch(@Body target : Branch) : Call<Boolean>

    @PUT("/spring/branch")
    fun modifyBranch(@Body newBranch : Branch) : Call<Boolean>

    @GET("/spring/user/worker/{id_branch}")
    fun getWorkerViewByIdBranch(@Path("id_branch") id_branch : Int) : Call<List<WorkerView>>

    @GET("/spring/work/{id_worker}/{id_branch}")
    fun getWorkerInfoByIdWorker(@Path("id_worker") id_worker : Int, @Path("id_branch") id_branch : Int) : Call<WorkerInfo>

    @POST("/spring/work/{id_branch}")
    fun createWorkerInfo(@Body newWorkerInfo : WorkerInfo, @Path("id_branch") id_branch : Int) : Call<Boolean>

    @PUT("/spring/work")
    fun modifyWorkerInfo(@Body newWorkerInfo : WorkerInfo) : Call<Boolean>

    @DELETE("/spring/work/delete/{id_worker}/{id_branch}")
    fun deleteWorkerInfo(@Path("id_worker") id_worker : Int, @Path("id_branch") id_branch : Int) : Call<Boolean>

    @GET("/spring/user/id_worker/{id_worker}")
    fun getAccountById(@Path("id_worker") id_worker : Int) : Call<String>


}