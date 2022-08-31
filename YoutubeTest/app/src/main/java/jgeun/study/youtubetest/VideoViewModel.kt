package jgeun.study.youtubetest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class VideoViewModel : ViewModel() {

    val videoId = MutableStateFlow<String>("f-5BqxqvknE")
}