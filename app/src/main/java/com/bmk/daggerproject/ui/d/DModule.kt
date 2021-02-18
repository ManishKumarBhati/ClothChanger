package com.bmk.daggerproject.ui.d

import com.bmk.daggerproject.ui.c.CView
import dagger.Module
import dagger.Provides

@Module
public class DModule {
    @Provides
    fun provideBFragment(fragment: DFragment): DView {
        return fragment
    }
}