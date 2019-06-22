
package com.dov.templateapp.di.modules

import android.content.Context
import com.dov.templateapp.repository.MovieRepository
import com.dov.templateapp.webservice.MovieService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Singleton
    @Provides
    fun provideMovieService(context: Context): MovieService {
      return MovieService(context)
    }

    @Singleton
    @Provides
    fun provideMovieRepo(testService: MovieService): MovieRepository {
        return MovieRepository(testService)
    }
}
