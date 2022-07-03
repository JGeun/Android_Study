package jgeun.study.commonlistadapter.my

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.commonlistadapter.databinding.ItemOneImageBinding
import jgeun.study.commonlistadapter.databinding.ItemOneLineTextBinding
import jgeun.study.commonlistadapter.databinding.ItemTwoLineTextBinding

sealed class CommonViewHolder(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: CommonItem)

    class OneLineTextViewHolder(
        private val binding: ItemOneLineTextBinding
    ) : CommonViewHolder(binding) {
        override fun bind(item: CommonItem) {
            val viewObject = item.viewObject as ViewObject.OneLineTextViewObject
            binding.content.text = viewObject.contents
        }
    }
    class TwoLineTextViewHolder(
        private val binding: ItemTwoLineTextBinding
    ) : CommonViewHolder(binding) {
        override fun bind(item: CommonItem) {
            val viewObject = item.viewObject as ViewObject.TwoLineTextViewObject
            binding.title.text = viewObject.title
            binding.content.text = viewObject.contents
        }
    }
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
}