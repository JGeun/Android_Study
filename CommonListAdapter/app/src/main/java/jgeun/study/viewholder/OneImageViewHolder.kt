package jgeun.study.viewholder

import com.bumptech.glide.Glide
import jgeun.study.commonlistadapter.data.CommonItem
import jgeun.study.commonlistadapter.databinding.ItemOneImageBinding
import jgeun.study.commonlistadapter.my.ViewObject

class OneImageViewHolder(
    private val binding: ItemOneImageBinding
) : CommonViewHolder(binding) {
    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as ViewObject.OneImageViewObject
        Glide.with(binding.root)
            .load(viewObject.imageVO.url)
            .into(binding.image)
    }
}