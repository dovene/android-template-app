package com.dov.templateapp.viewmodel

import android.content.Context
import com.dov.templateapp.repository.MovieRepository
import com.dov.templateapp.webservice.MovieService
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginFragmentViewModelTest {

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun callLoginServiceWithSuccess() {
        val loginFragmentViewModel  = LoginFragmentViewModel(MovieRepository(
            MovieService(mockContext)
        ))
        assertTrue(loginFragmentViewModel.canCallLoginService(true, "something", "something"))
    }

    @Test
    fun callLoginServiceFailure() {
    val loginFragmentViewModel  = LoginFragmentViewModel(MovieRepository(
        MovieService(mockContext)
    ))
       assertFalse(loginFragmentViewModel.canCallLoginService(true, null, null))
    }
}
