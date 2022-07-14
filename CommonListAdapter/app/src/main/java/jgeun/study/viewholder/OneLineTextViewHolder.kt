package jgeun.study.viewholder

import jgeun.study.commonlistadapter.data.CommonItem
import jgeun.study.commonlistadapter.data.viewobject.OneLineTextViewObject
import jgeun.study.commonlistadapter.databinding.ItemOneLineTextBinding

class OneLineTextViewHolder(
    private val binding: ItemOneLineTextBinding
) : CommonViewHolder(binding) {
    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as OneLineTextViewObject
        binding.content.text = viewObject.contents
    }
}