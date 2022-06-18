package jgeun.study.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import jgeun.study.recyclerview.adapter.CustomAdapterByBinding
import jgeun.study.recyclerview.adapter.CustomAdapterByFindId
import jgeun.study.recyclerview.databinding.ActivityMainBinding
import jgeun.study.recyclerview.model.Data

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //test data
    private val dataList = arrayListOf<Data>(
        Data("title1", "content1"),
        Data("title2", "content2"),
        Data("title3", "content3"),
        Data("title4", "content4"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*
        * layoutManager: RecycierView는 LayoutManager를 통해 정렬됨.
        *   - LinearLayoutManager: 1차원 목록 (수직, 수평)으로 정렬
        *   - GridLayoutManager: 모든 항목을 2차원 그리디로 정렬 (표)
        *       - 세로로 정렬된 경우 행마다 높이가 다를 수 있음
        *       - 가로로 정렬된 경우 행마다 너비가 다를 수 있음
        *   - StaggeredGridLayoutManager:
        *         GridLayoutManager와 비슷하지만 행의 항목이 너비 또는 높이가 동일할 필요가 없습니다
        *
        *  setHasFixedSize: RecyclerView의 크기 변경이 일정하다는 것을 설정함으로서
        *        RecyclerView가 변경될 때마다 크기가 변하고 이것으로 인해 레이아웃을
        *        다시 그리게 되는 작업을 피할 수 있도록 하여 성능 하락을 방지하는 기능.
        */
        binding.recyclewview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = CustomAdapterByBinding(this@MainActivity, dataList) //binding을 활용한 adapter 구현
//            adapter = CustomAdapterByFindId(this@MainActivity, dataList) //findViewById를 활용한 adapter 구현
        }
    }
}