package com.dov.templateapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.dov.templateapp.model.Movie
import com.dov.templateapp.paginationdata.MoviesDataSourcefactory

import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList
import javax.inject.Inject

class MovieListFragmentViewModel @Inject constructor() : ViewModel() {
    var movies = MutableLiveData<ArrayList<Movie>>()

    var movieList: LiveData<PagedList<Movie>>

    private val compositeDisposable = CompositeDisposable()

    private  val PAGE_SIZE = 2
    private  val INITIAL_LOAD_SIZE_HINT = 4

    private val dataSourceFactory : MoviesDataSourcefactory

    init {
        dataSourceFactory = MoviesDataSourcefactory(movies.value)
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setEnablePlaceholders(false)
            .build()
        movieList = LivePagedListBuilder(dataSourceFactory, config).build()
    }

   /* fun getJobState(): LiveData<JobState> = Transformations.switchMap<ParkingsDataSource, JobState>(
        dataSourceFactory.parkingsDataSourceLiveData) { it.jobState }

    fun filterData(parkings: ArrayList<Movie>){
        parkingsList?.value?.dataSource?.invalidate()
        dataSourceFactory.search(parkings)
    }*/

    fun refresh(movies: ArrayList<Movie>){
        movieList?.value?.dataSource?.invalidate()
        dataSourceFactory.refresh(movies)
    }


    fun setData(list: ArrayList<Movie>) {
        movies.postValue(list)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}