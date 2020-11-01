package com.bmk.daggerproject.ui.about

import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.ItemPlayersBinding
import com.bmk.daggerproject.domain.ResponseData
import com.bmk.daggerproject.util.setGlideImage
import com.xwray.groupie.databinding.BindableItem

class PlayersItem(val data: ResponseData) : BindableItem<ItemPlayersBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_players
    }

    override fun bind(viewBinding: ItemPlayersBinding, position: Int) {
        viewBinding.apply {
            data.imgUrl?.let { ivImg.setGlideImage(it) }
        }
    }
}