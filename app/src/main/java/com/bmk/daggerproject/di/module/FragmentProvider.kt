package com.bmk.daggerproject.di.module

import com.bmk.daggerproject.ui.a.AFragment
import com.bmk.daggerproject.ui.a.AModule
import com.bmk.daggerproject.ui.b.BFragment
import com.bmk.daggerproject.ui.b.BModule
import com.bmk.daggerproject.ui.c.CFragment
import com.bmk.daggerproject.ui.c.CModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [AModule::class])
    abstract fun providesAFragment(): AFragment

    @ContributesAndroidInjector(modules = [BModule::class])
    abstract fun providesBFragment(): BFragment

    @ContributesAndroidInjector(modules = [CModule::class])
    abstract fun providesCFragment(): CFragment

}