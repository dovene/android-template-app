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

class MovieDetailFragmentViewModel @Inject constructor() : ViewModel() {

    private val mutableFabClicked = MutableLiveData<Boolean>()

    fun getFabClicked(): LiveData<Boolean> {
        return mutableFabClicked
    }

    fun onFabClicked() {
        mutableFabClicked.postValue(true)
    }
}