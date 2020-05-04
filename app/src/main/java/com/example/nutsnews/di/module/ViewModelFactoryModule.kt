package com.example.nutsnews.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.nutsnews.di.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}