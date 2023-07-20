package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.NetworkResults
import com.example.myapplication.model.FacilitiesModel
import com.example.myapplication.repository.FacilitiesRepository

class FacilityViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var facilitiesRepository: FacilitiesRepository
    fun getFacilities() : LiveData<NetworkResults<FacilitiesModel>>{
        facilitiesRepository = FacilitiesRepository()
        return facilitiesRepository.getFacilities()
    }
}