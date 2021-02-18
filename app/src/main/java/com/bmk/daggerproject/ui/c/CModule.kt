package com.bmk.daggerproject.ui.c

import com.bmk.daggerproject.ui.b.BFragment
import com.bmk.daggerproject.ui.d.EmployeeInputRequest
import com.bmk.daggerproject.ui.d.PersonalInputRequest
import dagger.Module
import dagger.Provides

@Module
public class CModule {
    @Provides
    fun provideBFragment(fragment: CFragment): CView {
        return fragment
    }

    @Provides
    fun getPageData(fragment: CFragment): PersonalInputRequest? {
        return fragment.arguments?.getParcelable(CFragment.ARGS_PERSONAL_DATA)
            ?: error("Personal Data required")
    }
}