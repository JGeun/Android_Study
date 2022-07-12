package jgeun.study.viewholder

import jgeun.study.commonlistadapter.data.CommonItem
import jgeun.study.commonlistadapter.databinding.ItemTwoLineTextBinding
import jgeun.study.commonlistadapter.my.ViewObject

class TwoLineTextViewHolder(
    private val binding: ItemTwoLineTextBinding
) : CommonViewHolder(binding) {
    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as ViewObject.TwoLineTextViewObject
        binding.title.text = viewObject.title
        binding.content.text = viewObject.contents
    }
}