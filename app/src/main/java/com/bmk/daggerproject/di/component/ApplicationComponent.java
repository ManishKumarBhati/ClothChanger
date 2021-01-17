package com.bmk.daggerproject.di.component;

import com.bmk.daggerproject.BaseApplication;
import com.bmk.daggerproject.di.module.ActivityProvider;
import com.bmk.daggerproject.di.module.AppModule;
import com.bmk.daggerproject.di.module.FragmentProvider;
import com.bmk.data.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, AndroidSupportInjectionModule.class, FragmentProvider.class, NetworkModule.class, ActivityProvider.class})
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {


    @Override
    void inject(BaseApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApplication application);

        ApplicationComponent build();
    }
}