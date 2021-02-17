package com.bmk.daggerproject.ui.c

import dagger.Module
import dagger.Provides

@Module
public class CModule {
    @Provides
    fun provideBFragment(fragment: CFragment): CView {
        return fragment
    }
}