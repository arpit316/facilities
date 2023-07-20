package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.NetworkResults
import com.example.myapplication.model.FacilitiesModel
import com.example.myapplication.retrofit.ApiInterface
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FacilitiesRepository {
    private val facilitiesLiveData = MutableLiveData<NetworkResults<FacilitiesModel>>()
    private val call : Call<FacilitiesModel> = RetrofitClient.apiClient().create(ApiInterface::class.java).getFacilities()
    fun getFacilities() : MutableLiveData<NetworkResults<FacilitiesModel>>{
        call.enqueue(object  : Callback<FacilitiesModel>{
            override fun onResponse(
                call: Call<FacilitiesModel>,
                response: Response<FacilitiesModel>
            ) {
                if (response.isSuccessful){
                    Log.d("fetch", "onResponse: ${response.code()}")
                    Log.d("fetch", "onResponse: ${response.body()}")

                    facilitiesLiveData.postValue(NetworkResults.Success(response.body()))
                }
            }

            override fun onFailure(call: Call<FacilitiesModel>, t: Throwable) {
                Log.d("error1", "onFailure: ${t.message}")
                facilitiesLiveData.postValue(NetworkResults.Error(t.message))
            }

        })
        return facilitiesLiveData
    }
}