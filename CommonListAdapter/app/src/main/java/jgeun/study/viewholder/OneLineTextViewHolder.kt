package jgeun.study.viewholder

import jgeun.study.commonlistadapter.data.CommonItem
import jgeun.study.commonlistadapter.databinding.ItemOneLineTextBinding
import jgeun.study.commonlistadapter.my.ViewObject

class OneLineTextViewHolder(
    private val binding: ItemOneLineTextBinding
) : CommonViewHolder(binding) {
    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as ViewObject.OneLineTextViewObject
        binding.content.text = viewObject.contents
    }
}