package jgeun.study.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.recyclerview.R
import jgeun.study.recyclerview.model.Data

/*
*  findViewById를 활용한 adapter 구현
*/

//context: 연결될 Activity의 요소들을 가져오기 위한 context, dataList: 보여줄 data들
class CustomAdapterByFindId(private val context: Context, private val dataList: ArrayList<Data>)
    : RecyclerView.Adapter<CustomAdapterByFindId.ViewHolder>(){

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title: TextView
        val content: TextView

        init {
            title = view.findViewById(R.id.title)
            content = view.findViewById(R.id.content)
        }
    }

    // 새로운 view 생성
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_data, viewGroup, false)

        return ViewHolder(view)
    }

    // 생성된 뷰의 contents에 데이터 입력
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataList[position].title
        viewHolder.content.text = dataList[position].content
    }

    // 뷰들의 개수
    override fun getItemCount(): Int = dataList.size
}