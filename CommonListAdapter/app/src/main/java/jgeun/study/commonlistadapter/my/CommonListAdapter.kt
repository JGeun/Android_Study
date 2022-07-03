package jgeun.study.commonlistadapter.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.commonlistadapter.R

class CommonListAdapter(
    private val dataSet: ArrayList<CommonItem>
) : RecyclerView.Adapter<CommonViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return when(viewType) {
            CommonViewType.ONE_LINE_TEXT.ordinal -> {
                CommonViewHolder.OneLineTextViewHolder(
                    getViewDataBinding(parent, R.layout.item_one_line_text))
            }
            CommonViewType.TWO_LINE_TEXT.ordinal -> {
                CommonViewHolder.TwoLineTextViewHolder(
                    getViewDataBinding(parent, R.layout.item_two_line_text))
            }
            else -> {
                CommonViewHolder.OneImageViewHolder(
                    getViewDataBinding(parent, R.layout.item_one_image))
            }
        }
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return CommonViewType.valueOf(dataSet[position].viewType).ordinal
    }

    private fun <T: ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int) : T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    }
}