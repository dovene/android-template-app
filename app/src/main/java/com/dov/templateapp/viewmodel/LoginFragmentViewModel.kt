package com.dov.templateapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.dov.templateapp.model.MoviesResponse
import com.dov.templateapp.model.ResourceWrapper
import com.dov.templateapp.repository.MovieRepository
import timber.log.Timber
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {
    var mail = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private val mutableLiveDataLoginResult = MutableLiveData<ResourceWrapper<MoviesResponse>>()

    fun getLoginResult():LiveData<ResourceWrapper<MoviesResponse>>{
        return mutableLiveDataLoginResult
    }

    fun onLoginButtonClicked() {
        if (mail.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            mutableLiveDataLoginResult.postValue(ResourceWrapper.error("All values are mandatory", null, null))
        } else {
           getMovies()
        }

    }


    private fun getMovies(){
        movieRepository.setServiceToken("")
        movieRepository.getAllMovies({
            Timber.d("testRepository" + it.results.size)
            mutableLiveDataLoginResult.postValue(ResourceWrapper.success(it))
            //testData.postValue(ResourceWrapper.success(it))
        }, {
            Timber.d("testRepository" + it.message)
           // testData.postValue(ResourceWrapper.error("",it,null))
            mutableLiveDataLoginResult.postValue(ResourceWrapper.error(it.localizedMessage,null,null))
        })
    }
}