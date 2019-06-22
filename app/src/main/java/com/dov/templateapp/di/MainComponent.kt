package com.dov.templateapp.di

import android.app.Application
import com.dov.templateapp.application.TemplateApplication
import com.dov.templateapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Dagger main component for declaring various modules and initiating dependencies injection within Android Application subclass
 */

@Singleton
@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, ViewModelsModule::class,
    ActivitiesModule::class, FragmentsModule::class, RepositoriesModule::class])
interface MainComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): MainComponent
    }

    fun inject(prismeaApplication: TemplateApplication)
}