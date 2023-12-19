package com.example.find_movie_theater.ui.main.search

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.find_movie_theater.data.entities.Movie
import com.example.find_movie_theater.databinding.FragmentSearchBinding
import com.example.find_movie_theater.ui.BaseFragment
import com.example.find_movie_theater.ui.main.search.adapter.SearchRVAdapter

class SearchFragment(): BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    var movieList = arrayListOf(Movie(1, "actor1", "dddd",123), Movie(2, "actor2", "ddd",null), Movie(3, "actor3", "dddd",null), Movie(3, "actor3", "dddd",null))
    override fun initAfterBinding() {

        val SearchAdapter = SearchRVAdapter(movieList)
        binding.searchRvMovie.adapter = SearchAdapter
        binding.searchRvMovie.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.VERTICAL }

    }
}