package jgeun.study.maskinfo;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jgeun.study.maskinfo.model.Store;
import jgeun.study.maskinfo.model.StoreInfo;
import jgeun.study.maskinfo.repository.MaskService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    public MutableLiveData<List<Store>> itemLiveData = new MutableLiveData<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    private MaskService service = retrofit.create(MaskService.class);

    private Call<StoreInfo> storeInfoCall = service.fetchStoreInfo();

    public void fetchStoreInfo() {
        storeInfoCall.clone().enqueue(new Callback<StoreInfo>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<StoreInfo> call, Response<StoreInfo> response) {
                List<Store> items = response.body().getStores()
                        .stream()
                        .filter(item -> item.getRemain_stat() != null)
                        .collect(Collectors.toList());

                itemLiveData.postValue(items);
            }

            @Override
            public void onFailure(Call<StoreInfo> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                itemLiveData.postValue(Collections.emptyList());
            }
        });
    }
}
