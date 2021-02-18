package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.ItemABinding
import com.bmk.domain.DataResponse
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class AItem(
    val data: DataResponse,
    private val subject: PublishSubject<DataResponse>
) : BindableItem<ItemABinding>() {
    override fun getLayout() = R.layout.item_a


    override fun bind(viewBinding: ItemABinding, position: Int) {
        viewBinding.apply {
            tvName.text = "${data.lName} ${data.fName}"
            tvDob.text = "DOB: ${data.dob}"
            tvGender.text = "Gender: ${data.gender}"
            tvMob.text = "Mobile No: ${data.mob}"
            root.setOnClickListener { subject.onNext(data) }
        }
    }
}