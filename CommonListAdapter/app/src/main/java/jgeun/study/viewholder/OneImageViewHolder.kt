package jgeun.study.viewholder

import com.bumptech.glide.Glide
import jgeun.study.commonlistadapter.data.CommonItem
import jgeun.study.commonlistadapter.data.viewobject.OneImageViewObject
import jgeun.study.commonlistadapter.databinding.ItemOneImageBinding

class OneImageViewHolder(
    private val binding: ItemOneImageBinding
) : CommonViewHolder(binding) {
    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as OneImageViewObject
        Glide.with(binding.root)
            .load(viewObject.url)
            .into(binding.image)
    }
}