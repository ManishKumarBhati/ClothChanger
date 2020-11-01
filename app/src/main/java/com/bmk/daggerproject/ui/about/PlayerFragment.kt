package com.bmk.daggerproject.ui.about

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentPlayerBinding
import com.bmk.daggerproject.domain.ResponseData
import com.bmk.daggerproject.util.CommonFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import javax.inject.Inject


class PlayerFragment : CommonFragment(), PlayerContract {

    @Inject
    lateinit var presenter: PlayerPresenter
    lateinit var section: Section
    lateinit var binding: FragmentPlayerBinding
    override fun getLayout() = R.layout.fragment_player

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayerBinding.bind(view)
        presenter.start()
        initView()
    }

    private fun initView() {
        section = Section()
        binding.rvPlayersList.apply {
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = GroupAdapter<ViewHolder>().apply { add(section) }
        }
    }

    override fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun render(data: List<ResponseData>) {
        section.update(emptyList())

        val item = data.filter { it.imgUrl != null }.map { PlayersItem(it) }
        section.update(item)

    }

    override fun showErrorMessage(error: String?) {
        Log.e("Error", error)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    companion object {
        val TAG: String = "AFragment"
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }
}