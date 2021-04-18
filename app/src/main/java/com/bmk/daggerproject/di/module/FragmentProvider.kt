package com.bmk.daggerproject.di.module

import com.bmk.daggerproject.ui.a.AFragment
import com.bmk.daggerproject.ui.a.AModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [AModule::class])
    abstract fun providesAFragment(): AFragment
}