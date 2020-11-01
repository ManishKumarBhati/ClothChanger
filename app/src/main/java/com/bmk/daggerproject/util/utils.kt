package com.bmk.daggerproject.util

import android.widget.ImageView
import com.bmk.daggerproject.R
import com.bumptech.glide.Glide

fun ImageView.setGlideImage(url: String) {
    Glide
        .with(this)
        .load(url)
        .thumbnail(0.1f)
        .error(R.drawable.ic_image)
        .centerCrop()
        .placeholder(R.drawable.ic_image)
        .into(this)
}
