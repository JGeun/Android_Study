package jgeun.study.maskinfo_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jgeun.study.maskinfo_kotlin.model.Store
import jgeun.study.maskinfo_kotlin.repository.MaskService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: MaskService
) : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    init {
        fetchStoreInfo()
    }

    fun fetchStoreInfo() {
        loadingLiveData.value = true

        // ViewModel LifeScope 안에 있을 때 돌아감
        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo(37.5360, 126.9940)
            itemLiveData.value = storeInfo.stores

            loadingLiveData.value = false
        }
    }
}