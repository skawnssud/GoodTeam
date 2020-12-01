package com.example.app01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.DB.retrofitAPI
import com.example.app01.databinding.ActivityMainBinding
import com.example.app01.dto.Relation
import com.example.app01.dto.branch.Branch
import com.example.app01.dto.worker.Worker
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var mRetrofit: Retrofit
    private lateinit var mRetrofitAPI: retrofitAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initServer()
        for (i in 0..5) {
            var newOne : itemAnnouncement = itemAnnouncement(i,i,"Announcement " + i,"Description Test " + i,false)
            dataObject.listAnnouncement.add(newOne)
        }
        for (i in 1..5) {
            var newOne : itemJob = itemJob("Job test " + i)
            newOne.pay = i * 1000
            dataObject.listJob.add(newOne)
        }
        for (i in 1..5) {
            var newOne : Worker = Worker()
            newOne.age = 30 + i
            newOne.id = i + 1
            dataObject.listWorker.add(newOne)
        }
        dataObject.selectWorker = dataObject.listWorker[0]

        val binding  = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.myNavHostFragment)

    }
    fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
    fun getCurrentMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1
    fun getCurrentDay(): Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    fun getCurrentHour(): Int = Calendar.getInstance().get(Calendar.HOUR)
    fun getCurrentMinute(): Int = Calendar.getInstance().get(Calendar.MINUTE)
    // Server Default Setting
    fun initServer() {
        mRetrofit = Retrofit.Builder().baseUrl("http://222.106.121.250:8080")
            .addConverterFactory(GsonConverterFactory.create()).build()
        mRetrofitAPI = mRetrofit.create(retrofitAPI::class.java)
    }

    fun getRetrofitAPI() : retrofitAPI {
        return mRetrofitAPI
    }
    fun alertToast(massege : String) {
        Toast.makeText(applicationContext, massege, Toast.LENGTH_SHORT).show()
    }

    fun getBranchesByIdBoss(id_boss : Int) {
        var getBranches = Thread(Runnable {
            dataObject.listBranch = (mRetrofitAPI.searchBranchByIdBoss(id_boss).execute().body() as ArrayList<Branch>?)!!
        })
        getBranches.start()
        getBranches.join()
        if (dataObject.listBranch.size == 0) {
            var newBranch : Branch = Branch()
            newBranch.id_boss = dataObject.selectUser.id
            newBranch.title = "New Branch"
            createBranch(newBranch)
        }
        dataObject.selectBranch = dataObject.listBranch[0]
    }
    // Create Branch
    fun createBranch(newBranch : Branch) : Boolean {
        var success = false
        var duplication = false
        var createBranch = Thread(Runnable {
            success = mRetrofitAPI.createBranch(newBranch).execute().body()!!
        })
        if (dataObject.listBranch.size != 0) {
            dataObject.listBranch.forEach {
                if (it.title == newBranch.title) duplication = true
            }
            if (duplication) {
                alertToast("Title of Branch is duplicated. Please check again.")
                return false
            } else {
                dataObject.listBranch.add(newBranch)
                createBranch.start()
                createBranch.join()
                return true
            }
        } else {
            dataObject.listBranch.add(newBranch)
            createBranch.start()
            createBranch.join()
        }
        return success
    }

    fun modifyBranch(newBranch : Branch) : Boolean {
        var success = false
        var modifyBranch = Thread(Runnable {
            success = mRetrofitAPI.modifyBranch(newBranch).execute().body()!!
        })
        modifyBranch.start()
        modifyBranch.join()
        return success
    }

    fun deleteBranch(target : Branch) : Boolean {
        var success = false
        var deleteBranch = Thread(Runnable {
            success = mRetrofitAPI.deleteBranch(target).execute().body()!!
        })
        deleteBranch.start()
        deleteBranch.join()
        return success
    }

    fun getRelationByIdBranch(id_branch : Int) {
        var getRelations = Thread(Runnable {
            dataObject.listRelation = (mRetrofitAPI.searchRelationByIdBranch(id_branch).execute().body() as ArrayList<Relation>?)!!
        })
        getRelations.start()
        getRelations.join()
        if (dataObject.listRelation.size == 0) {
            // First worker = himself
            var newRelation : Relation = Relation()
            newRelation.id_branch = dataObject.selectBranch.id
            newRelation.id_worker = dataObject.selectUser.id
            createRelation(newRelation)
        }
        dataObject.selectRelation = dataObject.listRelation[0]

    }
    fun createRelation(newRelation: Relation) : Boolean {
        var success = false
        var duplication = false
        var createRelation = Thread(Runnable {
            success = mRetrofitAPI.createRelation(newRelation).execute().body()!!
        })
        if (dataObject.listRelation.size != 0) {
            dataObject.listRelation.forEach {
                if (it.id_worker == newRelation.id_worker) duplication = true
            }
            if (duplication) {
                alertToast("Relation is already existed. Please check again.")
                return false
            } else {
                dataObject.listRelation.add(newRelation)
                createRelation.start()
                createRelation.join()
                return true
            }
        } else {
            dataObject.listRelation.add(newRelation)
            createRelation.start()
            createRelation.join()
        }
        return success

    }
}