package com.dov.templateapp.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.dov.templateapp.R
import com.dov.templateapp.model.Movie
import com.dov.templateapp.view.activities.BaseActivity
import com.dov.templateapp.view.fragments.LoginFragment
import com.dov.templateapp.view.fragments.MovieListFragment

import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.detail_movie_fragment.*

class HomeActivity : BaseActivity() {

    companion object {
        val MOVIES: String = "MOVIES"
        fun newIntent(movies: ArrayList<Movie>, context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(MOVIES, movies)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceRootFragment(MovieListFragment.newInstance(movies,this))
        }
    }

    val movies: ArrayList<Movie> by lazy {
        intent.extras.getSerializable(MOVIES) as ArrayList<Movie>
    }

}
