package com.dov.templateapp.paginationdata

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dov.templateapp.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviesDataSourcefactory(private var resultsList : ArrayList<Movie>? = null)
    : DataSource.Factory<Int, Movie>(){
    val dataSourceLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val dataSource = MoviesDataSource(resultsList)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun refresh(bikes:ArrayList<Movie>) {
        resultsList = bikes
        create()
    }
}