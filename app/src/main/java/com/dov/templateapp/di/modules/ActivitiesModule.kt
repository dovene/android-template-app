package com.dov.templateapp.di.modules
import com.dov.templateapp.view.activities.HomeActivity
import com.dov.templateapp.view.activities.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger module class for declaring all activities that can have objects injected
 */
@Module
abstract class ActivitiesModule {

   /* @ContributesAndroidInjector
    abstract fun contributeStartActivity(): StartActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity*/


   @ContributesAndroidInjector
   abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

}