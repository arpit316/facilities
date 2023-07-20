package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FacilitiesItemsBinding
import com.example.myapplication.model.Facilities
import com.example.myapplication.model.FacilitiesModel

class FacilitiesAdapter(private val mList: ArrayList<Facilities>?) :
    RecyclerView.Adapter<FacilitiesAdapter.FacilitiesViewHolder>() {

    interface OnItemClicked {
        fun onClickedBtn(position: Int)
    }

    private lateinit var mListeners: OnItemClicked
    fun itemClicked(listeners: OnItemClicked) {
        mListeners = listeners
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilitiesViewHolder {
        val binding =
            FacilitiesItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FacilitiesViewHolder(binding, mListeners)
    }

    override fun onBindViewHolder(holder: FacilitiesViewHolder, position: Int) {
        holder.binding.facilityName.text = mList?.get(position)?.name
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class FacilitiesViewHolder(
        val binding: FacilitiesItemsBinding,
        listeners: OnItemClicked
    ) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.chooseOptionsBtn.setOnClickListener {
                listeners.onClickedBtn(adapterPosition)
            }
        }
    }
}