package com.dov.templateapp.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dov.templateapp.R
import com.dov.templateapp.databinding.DetailMovieFragmentBinding
import com.dov.templateapp.model.Movie
import com.dov.templateapp.view.activities.HomeActivity
import com.dov.templateapp.viewmodel.MovieDetailFragmentViewModel
import getImageUrl
import getPopularity
import kotlinx.android.synthetic.main.detail_movie_fragment.*
import share

class MovieDetailFragment : BaseFragment() {

    companion object {
        val MOVIE: String = "MOVIE"
        fun newInstance(movie: Movie): BaseFragment {
            val bundle = Bundle()
            bundle.putSerializable(MOVIE, movie)
            val fragment = MovieDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var model: MovieDetailFragmentViewModel
    private lateinit var binding: DetailMovieFragmentBinding
    val movie by lazy {
        arguments?.getSerializable(MOVIE) as Movie
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_movie_fragment, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieDetailFragmentViewModel::class.java)
        binding.movieDetailFragmentViewModel = model
        model.getFabClicked().observe(this, Observer {
            movie.share(context!!)
        })

        Glide.with(this.context!!)
            .load(movie?.getImageUrl(this.context!!))
            .centerCrop()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(image)
        titleTV?.text = movie?.title
        categoryTV?.text = movie?.getPopularity(this.context!!).toString()
        descriptionTV?.text = movie?.overview
        releaseDateTV?.text = movie?.release_date
        collapsingToolbar.title = movie?.title
        if (activity != null) {
            val activityHolder = activity as HomeActivity
            activityHolder.setSupportActionBar(toolbar)
            activityHolder.supportActionBar?.show()
            activityHolder.supportActionBar?.setDisplayShowTitleEnabled(true)
            activityHolder.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (activity != null) {
                    activity?.onBackPressed()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}