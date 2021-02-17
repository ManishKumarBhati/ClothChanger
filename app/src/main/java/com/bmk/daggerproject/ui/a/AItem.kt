package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.ItemABinding
import com.bmk.domain.ResponseData
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class AItem(
    val data: ResponseData,
    private val subject: PublishSubject<String>
) : BindableItem<ItemABinding>() {
    override fun getLayout(): Int {
        return R.layout.item_a
    }

    override fun bind(viewBinding: ItemABinding, position: Int) {
        viewBinding.apply {
            data.imgUrl?.let {
                ivImg.apply {
                    setOnClickListener { subject.onNext(data.id) }
                }
            }
        }
    }
}