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
    fun getPageData(fragment: BFragment): String? {
        return fragment.arguments?.getString(BFragment.ARGS_ID)
    }
}