package com.example.motion.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.motion.databinding.ImglListItemBinding
import com.example.motion.model.ImageModel

class ImgAdapter(private val list: List<ImageModel>,private val context:Context,val imgClickItem: ImgClickItem):RecyclerView.Adapter<ImgAdapter.ImgViewHolder>() {

    class ImgViewHolder(val imglListItemBinding: ImglListItemBinding):RecyclerView.ViewHolder(imglListItemBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val listItemBinding = ImglListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImgViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        val imgModel=list[position]
        val posterUrl = imgModel.imgUrl
        Glide.with(context).load(posterUrl).into(holder.imglListItemBinding.image)
        holder.imglListItemBinding.root.setOnClickListener {
            imgClickItem.onItemClick(imgModel,holder.imglListItemBinding.image)
        }

    }
}