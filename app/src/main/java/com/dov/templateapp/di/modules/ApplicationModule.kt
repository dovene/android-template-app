
package com.dov.templateapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Dagger module class for providing an Application context (Should require no or very little changes later)
 */
@Module
class ApplicationModule{
    @Provides
    fun providesApplicationContext(application: Application) : Context{
        return application
    }


}