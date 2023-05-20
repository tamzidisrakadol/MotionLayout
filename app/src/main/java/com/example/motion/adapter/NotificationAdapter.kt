package com.example.motion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motion.databinding.NotificationItemLayoutBinding
import com.example.motion.model.NotificationModel

class NotificationAdapter (private val list:List<NotificationModel>):RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(){

    class NotificationViewHolder(val binding: NotificationItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = NotificationItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notificationModelPos = list[position]
        holder.binding.notificationTitleTV.text = notificationModelPos.notificationTitle
        holder.binding.notificationNumberTV.text = notificationModelPos.notificationNumber.toString()
    }
}