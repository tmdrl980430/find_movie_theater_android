package com.example.find_movie_theater.ui.main.search.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.find_movie_theater.data.entities.Movie
import com.example.find_movie_theater.databinding.ItemMovieBinding


class SearchRVAdapter(private val movieList: ArrayList<Movie>) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(movie: Movie)
        fun onRemoveAlbum(position: Int)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    // 뷰홀더를 생성해줘야 할 때 호출되는 함수 => 아이템 뷰 객체를 만들어서 뷰홀더에 던져줍니다.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchRVAdapter.ViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(movie: Movie){
        movieList.add(movie)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(position: Int){
        movieList.removeAt(position)
        notifyDataSetChanged()
    }

    // 뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: SearchRVAdapter.ViewHolder, position: Int) {
        Log.d("리사이클러뷰",movieList[position].toString())


        holder.bind(movieList[position])
        //holder.itemView.setOnClickListener { mItemClickListener.onItemClick(movieList) }
//        holder.binding.itemAlbumTitleTv.setOnClickListener { mItemClickListener.onRemoveAlbum(position) } //삭제됐을 때
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = movieList.size

    inner class ViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        // 클릭 인터페이스 정의

        fun bind(movie: Movie){
            binding.itemMovieTitleTv.text = movie.title
            binding.itemMovieActorTv.text = movie.actor
        }
    }
}