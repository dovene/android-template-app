package com.dov.templateapp.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dov.templateapp.di.helper.ViewModelKey
import com.dov.templateapp.viewmodel.LoginFragmentViewModel
import com.dov.templateapp.viewmodel.MovieDetailFragmentViewModel
import com.dov.templateapp.viewmodel.MovieListFragmentViewModel
import com.dov.templateapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
* Dagger module class for declaring all viewmodels otherwise they will not be injected within activities/fragments-_-
*/

@Suppress("unused")
@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailFragmentViewModel::class)
    abstract fun bindMovieDetailFragmentViewModel(movieDetailFragmentViewModel: MovieDetailFragmentViewModel): ViewModel


    @Binds
   @IntoMap
   @ViewModelKey(MovieListFragmentViewModel::class)
   abstract fun bindMovieListViewModel(movieListFragmentViewModel: MovieListFragmentViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindLoginViewModel(loginFragmentViewModel: LoginFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
