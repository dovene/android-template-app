package com.dov.templateapp.di.modules

import com.dov.templateapp.view.fragments.LoginFragment
import com.dov.templateapp.view.fragments.MovieDetailFragment
import com.dov.templateapp.view.fragments.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger module class for declaring all fragment that can have objects injected
 */
@Suppress("unused")
@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}
