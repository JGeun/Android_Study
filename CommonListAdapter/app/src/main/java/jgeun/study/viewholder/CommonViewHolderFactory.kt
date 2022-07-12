package jgeun.study.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import jgeun.study.commonlistadapter.R
import jgeun.study.commonlistadapter.data.ViewType

object CommonViewHolderFactory {

    fun createViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
                return when(viewType) {
                    ViewType.ONE_LINE_TEXT.ordinal -> OneLineTextViewHolder(getViewDataBinding(parent, R.layout.item_one_line_text))
                    ViewType.TWO_LINE_TEXT.ordinal -> TwoLineTextViewHolder(getViewDataBinding(parent, R.layout.item_two_line_text))
                    else -> OneImageViewHolder(getViewDataBinding(parent, R.layout.item_one_image))
        }
    }

    private fun <T: ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int): T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    }
}