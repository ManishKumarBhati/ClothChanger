package com.bmk.data.module

import com.bmk.domain.MatchRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: com.bmk.data.MatchRepositoryImpl): MatchRepository
}