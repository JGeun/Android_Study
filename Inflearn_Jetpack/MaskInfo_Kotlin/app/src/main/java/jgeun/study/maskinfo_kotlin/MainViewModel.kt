package jgeun.study.maskinfo_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import jgeun.study.maskinfo_kotlin.model.Store
import jgeun.study.maskinfo_kotlin.repository.MaskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    lateinit var gson: Gson
    var service: MaskService

    init {
        gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        service = retrofit.create(MaskService::class.java)

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