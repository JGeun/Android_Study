package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.NewsAdapter
import com.example.retrofit.apikey.NewsApi
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.dto.SearchNewsDto
import com.example.retrofit.network.NewsClient
import com.example.retrofit.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val newsAdapter by lazy { NewsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNewsRv()
    }

    override fun onStart() {
        super.onStart()

        getNews()
    }

    private fun initNewsRv() {
        binding.rv.apply {
            setHasFixedSize(true)
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun getNews() {
        val start = System.currentTimeMillis()

        NewsClient.newsService.getSearchNews("apple", NewsApi.KEY)
            .enqueue(object : Callback<SearchNewsDto> {
                override fun onResponse(
                    call: Call<SearchNewsDto>,
                    response: Response<SearchNewsDto>
                ) {
                    if (response.isSuccessful) {
                        newsAdapter.addArticles(response.body()?.articles)
                        newsAdapter.notifyDataSetChanged()

                        val end = System.currentTimeMillis()

                        Log.e("$TAG time check", "${(end-start)/1000.0f}")
                    } else {
                        Utils.makeToast(this@MainActivity, response.message())
                    }
                }

                override fun onFailure(call: Call<SearchNewsDto>, t: Throwable) {
                    Utils.makeToast(this@MainActivity, t.message.toString() + " Failure")
                }
            })
    }
}
