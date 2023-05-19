package com.example.motion.adapter

import android.widget.ImageView
import com.example.motion.model.ImageModel

interface ImgClickItem {

    fun onItemClick(imageModel: ImageModel,imgview:ImageView)
}