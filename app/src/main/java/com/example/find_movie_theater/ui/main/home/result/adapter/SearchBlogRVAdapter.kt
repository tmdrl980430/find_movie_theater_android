package com.example.find_movie_theater.ui.main.search.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.find_movie_theater.data.blogsearch.BlogItem
import com.example.find_movie_theater.databinding.ItemBlogBinding
import com.example.find_movie_theater.ui.main.home.result.webViewActivity.WebViewActivity

class SearchBlogRVAdapter(private val blogList: ArrayList<BlogItem>) : RecyclerView.Adapter<SearchBlogRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(blog: BlogItem)
        fun onRemoveAlbum(position: Int)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    // 뷰홀더를 생성해줘야 할 때 호출되는 함수 => 아이템 뷰 객체를 만들어서 뷰홀더에 던져줍니다.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchBlogRVAdapter.ViewHolder {
        val binding: ItemBlogBinding = ItemBlogBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(blog: BlogItem){
        blogList.add(blog)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(position: Int){
        blogList.removeAt(position)
        notifyDataSetChanged()
    }

    // 뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: SearchBlogRVAdapter.ViewHolder, position: Int) {
        Log.d("리사이클러뷰",blogList.toString())


        holder.bind(blogList[position])
        //holder.itemView.setOnClickListener { mItemClickListener.onItemClick(movieList) }
//        holder.binding.itemAlbumTitleTv.setOnClickListener { mItemClickListener.onRemoveAlbum(position) } //삭제됐을 때
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = blogList.size

    inner class ViewHolder(val binding: ItemBlogBinding): RecyclerView.ViewHolder(binding.root){
        // 클릭 인터페이스 정의
        init {
            binding.itemBlogBloggerlinkTv.setOnClickListener {
                // 클릭한 링크에 대한 처리를 수행합니다.
                val blogItem = blogList[adapterPosition]
                // 여기에서 화면 전환 등의 동작을 수행하면 됩니다.
                // 예시: Intent를 사용한 화면 전환
                val intent = Intent(itemView.context, WebViewActivity::class.java)
                intent.putExtra("blogUrl", blogItem.link)
                itemView.context.startActivity(intent)
            }
        }
        fun bind(blog: BlogItem){


            binding.itemBlogTitleTv.text = changeHtmlText(blog.title)
            binding.itemBlogDescriptionTv.text = changeHtmlText(blog.description)
            //binding.itemBlogBloggerlinkTv.text = changeHtmlText(blog.link)
            binding.itemBlogPostdateTv.text = changeHtmlText(blog.postdate)

        }

        fun changeHtmlText(htmlText : String): String {
            val spannedText: Spanned =
                Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)

            return spannedText.toString()
        }


    }
}