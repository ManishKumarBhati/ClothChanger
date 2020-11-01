package com.bmk.daggerproject.util

import android.widget.ImageView
import com.bmk.daggerproject.R
import com.squareup.picasso.Picasso

fun ImageView.setImage(url: String) {
    Picasso.get()
        .load(url)
        .centerCrop()
        .resize(70, 70)
        .placeholder(R.drawable.ic_image)
        .error(R.drawable.ic_image)
        .into(this)
}
