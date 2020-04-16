package com.dov.templateapp.paginationdata

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.dov.templateapp.application.TemplateApplication
import com.dov.templateapp.model.Movie
import com.dov.templateapp.model.ResourceWrapper
import com.dov.templateapp.repository.MovieRepository
import com.dov.templateapp.webservice.MovieService
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MoviesDataSource (private var resultsList: ArrayList<Movie>? = null) : PositionalDataSource<Movie>() {


    private lateinit var MoviesList: ArrayList<Movie>

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movie>) {
        displayList(resultsList, params, callback)
    }

    private fun displayList(
        resultsList: ArrayList<Movie>?,
        params: LoadInitialParams,
        callback: LoadInitialCallback<Movie>
    ) {
        if (resultsList != null) {
            this.MoviesList = resultsList!!
            response(params, callback, null)
        }
    }

    private fun getMovies(){
       /* var movieRepository =  MovieRepository()
        movieRepository.setServiceToken("")
        movieRepository.getAllMovies({
            Timber.d("testRepository" + it.results.size)
            //mutableLiveDataLoginResult.postValue(ResourceWrapper.success(it))
            //testData.postValue(ResourceWrapper.success(it))
        }, {
            Timber.d("testRepository" + it.message)
            // testData.postValue(ResourceWrapper.error("",it,null))
           // mutableLiveDataLoginResult.postValue(ResourceWrapper.error(it.localizedMessage,null,null))
        })*/
    }


    private fun response(params: LoadInitialParams, callback: LoadInitialCallback<Movie>, throwable: Throwable?) {
        if (params.requestedLoadSize > MoviesList.size) {
            callback.onResult(MoviesList.subList(0, MoviesList.size), 0)
        } else {
            callback.onResult(MoviesList.subList(0, params.requestedLoadSize), 0)
        }
    }


    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movie>) {
        if (params.loadSize + params.startPosition > MoviesList.size) {
            callback.onResult(MoviesList.subList(params.startPosition, MoviesList.size))
        } else {
            callback.onResult(MoviesList.subList(params.startPosition, params.loadSize + params.startPosition))
        }
    }


}