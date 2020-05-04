package com.example.nutsnews.di.component

import android.app.Application
import com.example.nutsnews.application.NutsNewsApplication
import com.example.nutsnews.di.module.ActivityBindingModule
import com.example.nutsnews.di.module.AppDatabaseModule
import com.example.nutsnews.di.module.NetworkModule
import com.example.nutsnews.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppDatabaseModule::class,
        NetworkModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<NutsNewsApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    override fun inject(newsApp: NutsNewsApplication)
}
