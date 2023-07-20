package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class FacilitiesModel(
    @SerializedName("facilities")
    val facilities: ArrayList<Facilities>,
    @SerializedName("exclusions")
    val exclusions: ArrayList<ArrayList<Exclusions>>
)
data class Facilities(
    @SerializedName("facility_id")
    val facilityId : String,
    val name : String,
    @SerializedName("options")
    val options : ArrayList<Options>
)
data class Options(
    val name : String,
    val icon : String,
    val id : String
){
    override fun toString(): String {
        return name
    }
}
data class Exclusions(
    @SerializedName("facility_id")
    val facilityIdEx: String,
    @SerializedName("options_id")
    val optionId : String
)
