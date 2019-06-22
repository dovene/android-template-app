package com.dov.templateapp.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dov.templateapp.R
import com.dov.templateapp.adapter.MoviesAdapter
import com.dov.templateapp.model.Movie
import com.dov.templateapp.view.activities.BaseActivity
import com.dov.templateapp.view.activities.HomeActivity
import com.dov.templateapp.viewmodel.LoginFragmentViewModel
import com.dov.templateapp.viewmodel.MovieListFragmentViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.detail_movie_fragment.*
import kotlinx.android.synthetic.main.movie_list.*
import kotlinx.android.synthetic.main.movie_list.view.*

class MovieListFragment : BaseFragment() {

    companion object {
        val MOVIES: String = "MOVIES"
        fun newInstance(movies: ArrayList<Movie>, context: Context): BaseFragment {
            val bundle = Bundle()
            bundle.putSerializable(MOVIES, movies)
            val fragment = MovieListFragment()
            fragment.arguments = bundle
            return fragment

        }
    }

    val movies by lazy {
        arguments?.getSerializable(MOVIES) as ArrayList<Movie>
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.movie_list, container, false)
        if (activity != null) {
            val activityHolder = activity as HomeActivity
            activityHolder.supportActionBar?.hide()
        }
        return view
    }

    private lateinit var adapter: MoviesAdapter
    private lateinit var model: MovieListFragmentViewModel
    private lateinit var disposable: Disposable


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRV.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieListFragmentViewModel::class.java)
        model.refresh(movies)
        moviesRV.isNestedScrollingEnabled = false
        initDataRetrieval()
    }


    fun initDataRetrieval() {
        adapter = MoviesAdapter(context!!) {
            if (activity != null) {
                val activityHolder = activity as HomeActivity
                activityHolder.addRootFragment(MovieDetailFragment.newInstance(it))
            }
        }
        moviesRV.adapter = adapter
        model.movieList.observe(this, android.arch.lifecycle.Observer {
            adapter.submitList(it)
        })


    }


}