package com.example.nutsnews.application

import android.app.Application
import com.example.nutsnews.di.ApplicationInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class NutsNewsApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        ApplicationInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}