package jgeun.study.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.commonlistadapter.data.CommonItem

abstract class CommonViewHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(item: CommonItem)
}