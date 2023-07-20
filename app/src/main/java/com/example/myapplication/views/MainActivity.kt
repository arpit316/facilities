package com.example.myapplication.views

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.NetworkResults
import com.example.myapplication.R
import com.example.myapplication.adapter.FacilitiesAdapter
import com.example.myapplication.adapter.OptionsAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Exclusions
import com.example.myapplication.model.Facilities
import com.example.myapplication.model.FacilitiesModel
import com.example.myapplication.model.Options
import com.example.myapplication.viewmodels.FacilityViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var facilityViewModel: FacilityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        facilityViewModel = ViewModelProvider(this)[FacilityViewModel::class.java]
        getFacilities()
        binding.facilitiesRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun getFacilities() {
        facilityViewModel.getFacilities().observe(this) {
            when (it) {
                is NetworkResults.Error -> {

                }

                is NetworkResults.Loading -> {

                }

                is NetworkResults.Success -> {

                    val adapter = FacilitiesAdapter(it.data?.facilities)
                    binding.facilitiesRecyclerView.adapter = adapter
                    adapter.itemClicked(object : FacilitiesAdapter.OnItemClicked {
                        override fun onClickedBtn(position: Int) {
                            val dialog = Dialog(this@MainActivity).apply {
                                setContentView(R.layout.dialog_facility)
                                window?.setLayout(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                )
                            }
                            val facility = it.data?.facilities?.get(position)
                            val listView = dialog.findViewById<ListView>(R.id.listView)

                            val optionsList = facility?.options ?: emptyList()
                            val exclusion = it.data?.exclusions
                            val facilities = it.data?.facilities?.get(position)
                            val optionsAdapter =
                                OptionsAdapter(optionsList, exclusion!!, facilities!!)

                            optionsAdapter.getSelectedOption()

                            listView.adapter = optionsAdapter
                            dialog.show()
                        }
                    })
                }
            }
        }
    }
}

