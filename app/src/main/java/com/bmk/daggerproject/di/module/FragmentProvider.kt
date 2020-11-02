package com.bmk.daggerproject.di.module

import com.bmk.daggerproject.ui.about.AFragment
import com.bmk.daggerproject.ui.about.AModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by manish on 07/07/201820.
 */
@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [AModule::class])
    abstract fun providesPlayerFragment(): AFragment

}