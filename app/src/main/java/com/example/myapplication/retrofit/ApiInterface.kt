package com.example.myapplication.retrofit

import com.example.myapplication.model.FacilitiesModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("iranjith4/ad-assignment/db")
    fun getFacilities() : Call<FacilitiesModel>
}