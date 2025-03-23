package com.example.petcare.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcare.databinding.ScheduleItemBinding
import com.example.petcare.model.ScheduleModel

class ScheduleAdapter(var data: List<ScheduleModel>) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    var mListener: RVClickListener? = null

    inner class ViewHolder(val binding: ScheduleItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnDeleteClick(listener: RVClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(data[position]) {
                binding.date.text = this.date
                binding.title.text = this.title
                binding.desc.text = this.desc

                binding.deleteBtn.setOnClickListener {
                    mListener?.onDeleteClick(this)
                }
            }
        }
    }
}

interface RVClickListener {
    fun onDeleteClick(data: ScheduleModel)
}