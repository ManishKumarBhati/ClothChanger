package com.bmk.daggerproject.ui.a


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bmk.daggerproject.R
import com.bmk.domain.UserData
import com.bumptech.glide.Glide

class ViewPagerAdapter internal constructor(
    val context: Context,
    val data: List<UserData>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.swipe_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.setImage(data[position].image, context)
    }

    override fun getItemCount() = data.size


    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView = itemView.findViewById(R.id.iv_image)


        fun setImage(iv: String, context: Context) {
            Glide.with(context).load(iv).into(ivImage)
        }
    }
}