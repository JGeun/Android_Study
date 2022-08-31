package jgeun.study.youtubetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import jgeun.study.youtubetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,  R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        binding.viewModel = viewModel

        lifecycle.addObserver(binding.youtubeVideo)
        binding.youtubeVideo.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = viewModel.videoId.value
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })
    }
}