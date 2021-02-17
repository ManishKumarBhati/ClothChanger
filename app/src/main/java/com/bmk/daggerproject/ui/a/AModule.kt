package com.bmk.daggerproject.ui.a

import dagger.Module
import dagger.Provides

@Module
public class AModule {

    @Provides
    fun provideAFragment(fragment: AFragment): AContract {
        return fragment
    }
}