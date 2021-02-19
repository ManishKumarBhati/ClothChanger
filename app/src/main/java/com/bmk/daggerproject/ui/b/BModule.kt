package com.bmk.daggerproject.ui.b

import dagger.Module
import dagger.Provides

@Module
public class BModule {
    @Provides
    fun provideBFragment(fragment: BFragment): BView {
        return fragment
    }

    @Provides
    fun getPageData(fragment: BFragment): Long? {
        return fragment.arguments?.getLong(BFragment.ARGS_DATA_ID)
            ?: error("Personal Data required")
    }
}