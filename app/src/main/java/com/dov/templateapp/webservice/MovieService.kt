package com.dov.templateapp.webservice

import android.content.Context
import com.dov.templateapp.R
import com.dov.templateapp.model.Movie
import com.dov.templateapp.model.MoviesResponse
import com.dov.templateapp.webservice.common.BaseService
import io.reactivex.Observable
import retrofit2.http.GET
import javax.inject.Inject

class MovieService @Inject constructor(val context : Context) : BaseService() {
    private var token : String =""

    fun getToken():String{
        return token
    }
    fun setToken(token : String){
        this.token = token
    }

    interface MovieApi {
        @GET("3/trending/all/day?api_key=0675d2721647d913c9b1329c8823a3cb")
        fun getMovies(): Observable<MoviesResponse>
    }

    override fun getApiClass(): Class<*> {
        return  MovieApi::class.java
    }

    override fun getRootUrl(): String {
        return context.getString(R.string.root_url)
    }

   /* override fun getHeaders(): Map<String, String> {
        return mapOf("Authorization" to getToken())
    }
*/

}