package jgeun.study.commonlistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class CommonListAdapter(
    private val dataSet: ArrayList<CommonItem>
) : RecyclerView.Adapter<CommonViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        /* 새로운 뷰타입이 생길 때마다 분기를 추가 */
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

    /* CommonViewType에서 해당 data의 viewType의 ordinal(인덱스)를 반환  */
    override fun getItemViewType(position: Int): Int {
        return CommonViewType.valueOf(dataSet[position].viewType).ordinal
    }

    /* binding 생성 */
    private fun <T: ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int) : T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    }
}