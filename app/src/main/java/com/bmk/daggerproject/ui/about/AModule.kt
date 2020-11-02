package com.bmk.daggerproject.ui.about

import dagger.Module
import dagger.Provides

@Module
public class AModule {

    @Provides
    fun provideAFragment(fragment: AFragment): AContract {
        return fragment
    }
}