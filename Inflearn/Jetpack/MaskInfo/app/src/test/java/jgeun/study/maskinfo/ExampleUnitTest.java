package jgeun.study.maskinfo;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import java.io.IOException;

import jgeun.study.maskinfo.model.StoreInfo;
import jgeun.study.maskinfo.repository.MaskService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void retrofitText() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MaskService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        MaskService service = retrofit.create(MaskService.class);

        /*Call<StoreInfo> storeInfoCall = service.fetchStoreInfo();

        StoreInfo storeInfo = storeInfoCall.execute().body();

        System.out.println(storeInfo.getCount());
        assertEquals(222, storeInfo.getCount());
        assertEquals(222, storeInfo.getStores().size());*/
    }
}