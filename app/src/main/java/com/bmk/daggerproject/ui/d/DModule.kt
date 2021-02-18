package com.bmk.daggerproject.ui.d

import com.bmk.daggerproject.ui.c.CFragment
import com.bmk.daggerproject.ui.c.CView
import dagger.Module
import dagger.Provides

@Module
public class DModule {
    @Provides
    fun provideBFragment(fragment: DFragment): DView {
        return fragment
    }

    @Provides
    fun getPageData(fragment: DFragment): EmployeeInputRequest? {
        return fragment.arguments?.getParcelable(DFragment.ARGS_EMP_DATA)
            ?: error("EMP Data required")
    }
}