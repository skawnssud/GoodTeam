package com.example.app01.DB

import com.example.app01.dto.User
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.worker.Work
import com.example.app01.dto.workerdetail.WorkerDetail
import com.example.app01.dto.worker.WorkerInfo
import com.example.app01.dto.workerview.WorkerView
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

    @GET("/spring/branch/get/{title}/{id_boss}")
    fun getIdBranch(@Path("title") title : String, @Path("id_boss") id_boss: Int) : Call<Int>

    @POST("/spring/branch")
    fun createBranch(@Body newBranch : Branch) : Call<Boolean>

    @DELETE("/spring/branch/{id_branch}")
    fun deleteBranch(@Path("id_branch") id_branch: Int) : Call<Boolean>

    @PUT("/spring/branch")
    fun modifyBranch(@Body newBranch : Branch) : Call<Boolean>

    @GET("/spring/user/worker/{id_branch}")
    fun getWorkerViewByIdBranch(@Path("id_branch") id_branch : Int) : Call<List<WorkerView>>

    @GET("/spring/work/{id_worker}/{id_branch}")
    fun searchWorkerInfoByIdWorker(@Path("id_worker") id_worker : Int, @Path("id_branch") id_branch : Int) : Call<WorkerInfo>

    @GET("/spring/work/info/{id_worker}")
    fun getWorkerInfoByIdWorker(@Path("id_worker") id_worker: Int) : Call<List<WorkerInfo>>

    @GET("/spring/branch/{id_branch}")
    fun getBranchByIdBranch(@Path("id_branch") id_branch: Int) : Call<Branch>

    @POST("/spring/work/{id_branch}")
    fun createWorkerInfo(@Body newWorkerInfo : WorkerInfo, @Path("id_branch") id_branch : Int) : Call<Boolean>

    @PUT("/spring/work")
    fun modifyWorkerInfo(@Body newWorkerInfo : WorkerInfo) : Call<Boolean>

    @DELETE("/spring/work/delete/{id_worker}/{id_branch}")
    fun deleteWorkerInfo(@Path("id_worker") id_worker : Int, @Path("id_branch") id_branch : Int) : Call<Boolean>

    @GET("/spring/user/id_worker/{id_worker}")
    fun getAccountById(@Path("id_worker") id_worker : Int) : Call<String>

    @GET("/spring/work/work/{id_workerinfo}")
    fun getWorkByIdWorkerInfo(@Path("id_workerinfo") id_workerinfo : Int) : Call<List<Work>>

    @POST("/spring/work/work")
    fun createWorkByIdWorkerInfo(@Body work : Work) : Call<Boolean>

    @PUT("/spring/work/work")
    fun modifyWork(@Body work : Work) : Call<Boolean>

    @DELETE("/spring/work/work/{id_workerinfo}")
    fun deleteWorkByIdWorkerInfo(@Path("id_workerinfo") id_workerinfo: Int) : Call<Boolean>

    @GET("/spring/work/work/id_branch/{id_branch}")
    fun getWorkersByIdBranch(@Path("id_branch") id_branch: Int) : Call<List<WorkerInfo>>

    @POST("/spring/work/detail")
    fun createWorkerDetail(@Body item : WorkerDetail) : Call<Boolean>

    @PUT("/spring/work/detail")
    fun modifyWorkerDetail(@Body item : WorkerDetail) : Call<Boolean>

    @GET("/spring/work/detail/{id_workerInfo}")
    fun getWorkerDetailByIdWorkerInfo(@Path("id_workerInfo") id_workerinfo: Int) : Call<WorkerDetail>






}