package jgeun.study.commonlistadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.commonlistadapter.data.ViewType
import jgeun.study.commonlistadapter.R
import jgeun.study.commonlistadapter.data.CommonItem
import jgeun.study.viewholder.CommonViewHolder
import jgeun.study.viewholder.CommonViewHolderFactory

class CommonListAdapter(
    private val dataSet: ArrayList<CommonItem>
) : RecyclerView.Adapter<CommonViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        /* 새로운 뷰타입이 생길 때마다 분기를 추가 */
        return CommonViewHolderFactory.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    /* CommonViewType에서 해당 data의 viewType의 ordinal(인덱스)를 반환  */
    override fun getItemViewType(position: Int): Int {
        return ViewType.valueOf(dataSet[position].viewType).ordinal
    }
}