package com.dov.templateapp.model

import java.io.Serializable

data class MoviesResponse (

    val page : Int,
    val results : List<Movie>,
    val total_pages : Int,
    val total_results : Int
) : Serializable