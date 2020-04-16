package com.dov.templateapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dov.templateapp.model.MoviesResponse
import com.dov.templateapp.model.ResourceWrapper
import com.dov.templateapp.repository.MovieRepository
import timber.log.Timber
import javax.inject.Inject


class LoginFragmentViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {
    var mail = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private val mutableLiveDataLoginResult = MutableLiveData<ResourceWrapper<MoviesResponse>>()
    private val mutableLiveDataProcessing = MutableLiveData<Boolean>()
    private val liveDataMerger: MediatorLiveData<*> = MediatorLiveData<Any>()

    fun getLoginResult():LiveData<ResourceWrapper<MoviesResponse>>{
        return mutableLiveDataLoginResult
    }

    fun getProcessState():LiveData<Boolean>{
        return mutableLiveDataProcessing
    }

    init {
        mutableLiveDataProcessing.postValue(false)
    }

    fun onLoginButtonClicked() {
        if (canCallLoginService(mutableLiveDataProcessing.value, mail.value,password.value)){
            getMovies()
        }
        if(mutableLiveDataProcessing.value == true){
            return
        }
        if (mail.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            mutableLiveDataLoginResult.postValue(ResourceWrapper.error("All values are mandatory", null, null))
        }
    }

     fun canCallLoginService(onGoingServiceCallState : Boolean?, mail : String?, password: String?): Boolean {
        return !mail.isNullOrEmpty() && !password.isNullOrEmpty() && onGoingServiceCallState == false
    }


    private fun getMovies(){
        movieRepository.setServiceToken("")

        mutableLiveDataProcessing.postValue(true)
        movieRepository.getAllMovies({
            Timber.d("testRepository" + it.results.size)
            mutableLiveDataLoginResult.postValue(ResourceWrapper.success(it))
            //testData.postValue(ResourceWrapper.success(it))

            mutableLiveDataProcessing.postValue(false)
        }, {
            Timber.d("testRepository" + it.message)
           // testData.postValue(ResourceWrapper.error("",it,null))
            mutableLiveDataLoginResult.postValue(ResourceWrapper.error(it.localizedMessage,null,null))
            mutableLiveDataProcessing.postValue(false)
        })
    }
}