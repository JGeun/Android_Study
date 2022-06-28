package com.example.retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.retrofit.data.NewsData
import com.example.retrofit.databinding.ItemNewsBinding
import com.example.retrofit.util.Utils

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val articles = mutableListOf<NewsData>()

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, newsData: NewsData) {
            binding.articleTitle.text = newsData.title
            binding.articleDate.text = newsData.publishedAt.split("T")[0]
            Glide.with(context)
                .load(newsData.urlToImage)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(binding.articleImage)
            binding.root.setOnClickListener {
                Utils.makeToast(context,"adapter click")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, articles[position])
    }

    override fun getItemCount(): Int = articles.size

    fun addArticles(newsDataSet: Array<NewsData>?) {
        if (newsDataSet != null) {
            for(newsData in newsDataSet)
                articles.add(newsData)
        }
    }
}