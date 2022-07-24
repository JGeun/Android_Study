package jgeun.study.maskinfo_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.maskinfo_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val storeAdapter = StoreAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = storeAdapter
        }

        viewModel.apply {
            itemLiveData.observe(this@MainActivity) {
                storeAdapter.updateItems(it)
            }

            loadingLiveData.observe(this@MainActivity) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchStoreInfo()
    }
}