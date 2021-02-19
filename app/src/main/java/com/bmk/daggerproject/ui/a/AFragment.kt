package com.bmk.daggerproject.ui.a

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentABinding
import com.bmk.daggerproject.ui.b.BFragment
import com.bmk.daggerproject.ui.main.MainActivity
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.domain.DataResponse
import com.jakewharton.rxbinding3.view.clicks
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class AFragment : CommonFragment(), AContract {

    @Inject
    lateinit var presenter: APresenter
    lateinit var section: Section
    lateinit var binding: FragmentABinding
    val subject = PublishSubject.create<DataResponse>()
    override fun getLayout() = R.layout.fragment_a

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentABinding.bind(view)
        presenter.start()
        initView()
    }

    private fun initView() {
        section = Section()
        binding.rvPlayersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = GroupAdapter<ViewHolder>().apply { add(section) }
        }
    }

    override fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun showErrorMessage(error: String?) {
        Log.e("Error", error)
    }

    override fun render(data: List<DataResponse>) {
        section.update(emptyList())
        val item = data.map { AItem(it, subject) }
        section.update(item)
    }

    override fun navigateToDetail(id: Long) {
        activity?.let {
            if (it.supportFragmentManager.findFragmentByTag(BFragment.TAG) == null) {
                it.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .setCustomAnimations(
                        MainActivity.AnimType.FADE.getAnimPair().first,
                        MainActivity.AnimType.FADE.getAnimPair().second
                    )
                    .add(
                        R.id.frame,
                        BFragment.newInstance(id),
                        BFragment.TAG
                    )
                    .commit()
            }
        }
    }

    override fun onAddClick(): Observable<Unit> {
        return binding.fabAdd.clicks()
    }

    override fun onitemClick(): Observable<DataResponse> {
        return subject
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    companion object {
        val TAG: String = "AFragment"
        fun newInstance(): AFragment {
            return AFragment()
        }
    }
}