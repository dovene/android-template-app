package com.dov.templateapp.repository

import com.dov.templateapp.model.Movie
import com.dov.templateapp.model.MoviesResponse
import com.dov.templateapp.webservice.MovieService
import com.dov.templateapp.webservice.common.BaseServiceDisposableObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(private val movieService: MovieService) : MovieService.MovieApi{

    override fun getMovies(): Observable<MoviesResponse> {
        return (movieService.getApi() as MovieService.MovieApi).getMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }



    fun getAllMovies(OnSuccess : (MoviesResponse)->Unit, OnError : (Throwable)->Unit) : DisposableObserver<MoviesResponse> {
        return getMovies().subscribeWith(object : BaseServiceDisposableObserver<MoviesResponse>(){
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
                super.onError(e)
                OnError(e)
                Timber.d("ErrorHandling"+errorResponse?.message)
            }

            override fun onNext(t: MoviesResponse) {
                OnSuccess(t)
            }
        })
    }

    fun setServiceToken(token:String){
        movieService.setToken(token)
    }


}