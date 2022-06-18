package jgeun.study.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.recyclerview.databinding.ItemDataBinding
import jgeun.study.recyclerview.model.Data

/*
*  binding을 활용한 adapter 구현
*/

//context: 연결될 Activity의 요소들을 가져오기 위한 context, dataList: 보여줄 data들
class CustomAdapterByBinding(private val context: Context, private val dataList: ArrayList<Data>)
    : RecyclerView.Adapter<CustomAdapterByBinding.ViewHolder>(){

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Data) {
            binding.title.text = data.title
            binding.content.text = data.content
        }
    }

    // view들 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // view의 content들에 데이터를 넣어줌.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    // item의 개수
    override fun getItemCount(): Int = dataList.size
}