package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.model.Exclusions
import com.example.myapplication.model.Facilities
import com.example.myapplication.model.Options


class OptionsAdapter(
    private val optionsList: List<Options>,
    private val exclusions: ArrayList<ArrayList<Exclusions>>,
    private val facility: Facilities
) : BaseAdapter() {
    private var selectedPosition = -1
    override fun getCount(): Int {
        return optionsList.size
    }

    override fun getItem(position: Int): Any {
        return optionsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.option_items, parent, false)

        val option = optionsList[position]
        val binding = ViewHolder(view)
        binding.nameTextView.text = option.name
        binding.iconImageView.setImageResource(getImageResource(option.icon))
        binding.radioButton.isChecked = position == selectedPosition

        binding.radioButton.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
        val disableOption = excludeOptions(option, exclusions)
        binding.apply {
            view.alpha = if (disableOption) 0.5f else 1.0f
            view.isEnabled = !disableOption
            radioButton.isEnabled = !disableOption
        }
        return view
    }


    private fun excludeOptions(option: Options, exclusions: List<List<Exclusions>>): Boolean {
        val facilityId = facility.facilityId
        val optionId = option.id

        return exclusions.any { exclusionsList ->
            exclusionsList.any { exclusion ->
                exclusion.facilityIdEx == facilityId && exclusion.optionId == optionId
            }
        }
    }

    private fun getImageResource(icon: String): Int {

        return when (icon) {
            "apartment" -> R.drawable.apartment3x
            "condo" -> R.drawable.condo3x
            "boat" -> R.drawable.boat3x
            "land" -> R.drawable.land3x
            "garage" -> R.drawable.garage3x
            "garden" -> R.drawable.garden3x
            "no-room" -> R.drawable.no_room3x
            "swimming" -> R.drawable.swimming3x
            "rooms" -> R.drawable.rooms3x
            else -> R.drawable.ic_launcher_background
        }
    }

    fun getSelectedOption(): Options? {
        return if (selectedPosition != -1) optionsList[selectedPosition] else null
    }

    private class ViewHolder(view: View) {
        val nameTextView: TextView = view.findViewById(R.id.textView)
        val iconImageView: ImageView = view.findViewById(R.id.imageView)
        val radioButton: RadioButton = view.findViewById(R.id.radioBtn)
    }
}