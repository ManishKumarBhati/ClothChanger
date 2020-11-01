package com.bmk.daggerproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module

@Module
public abstract class ActivityModule() {
    @Binds
    abstract fun provideAppCompatActivity(activity: MainActivity): AppCompatActivity
}