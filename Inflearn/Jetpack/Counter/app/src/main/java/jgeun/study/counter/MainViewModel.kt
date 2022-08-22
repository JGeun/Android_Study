package jgeun.study.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val handle: SavedStateHandle) : ViewModel(){
    private var count = handle.get<Int>("count") ?: 0
        set(value) {
            field = value
            countLiveData.value = value
            handle.set("count", value)
        }

    val countLiveData = MutableLiveData<Int>()

    fun increaseCount() {
        count++
    }

    fun decreaseCount() {
        count--
    }

}