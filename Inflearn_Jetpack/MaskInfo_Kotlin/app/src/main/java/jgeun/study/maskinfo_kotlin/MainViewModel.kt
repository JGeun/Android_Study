package jgeun.study.maskinfo_kotlin

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import jgeun.study.maskinfo_kotlin.model.Store
import jgeun.study.maskinfo_kotlin.repository.MaskService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: MaskService,
    private var funsedLocationClient : FusedLocationProviderClient
) : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    init {
        fetchStoreInfo()
    }

    @SuppressLint("MissingPermission")
    fun fetchStoreInfo() {
        loadingLiveData.value = true

        funsedLocationClient.lastLocation.addOnSuccessListener { location ->
            viewModelScope.launch {
                val storeInfo = service.fetchStoreInfo(location.latitude, location.longitude)
                itemLiveData.value = storeInfo.stores.filter { it.remain_stat != null }

                loadingLiveData.value = false
            }
        }.addOnFailureListener { exception ->
            loadingLiveData.value = false
        }

        // ViewModel LifeScope 안에 있을 때 돌아감

    }
}