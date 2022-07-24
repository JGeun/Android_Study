package jgeun.study.maskinfo_kotlin

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.maskinfo_kotlin.model.Store
import java.lang.String

class StoreAdapter : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {
    private var mItems: List<Store> = ArrayList<Store>()

    // 아이템 뷰 정보를 가지고 있는 클래스
    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView
        var addressTextView: TextView
        var distanceTextView: TextView
        var remainTextView: TextView
        var countTextView: TextView

        init {
            nameTextView = itemView.findViewById(R.id.name_text_view)
            addressTextView = itemView.findViewById(R.id.addr_text_view)
            distanceTextView = itemView.findViewById(R.id.distance_text_view)
            remainTextView = itemView.findViewById(R.id.remain_text_view)
            countTextView = itemView.findViewById(R.id.count_text_view)
        }
    }

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store: Store = mItems[position]
        holder.nameTextView.setText(store.name)
        holder.addressTextView.setText(store.addr)
        holder.distanceTextView.text = "1Km"//String.format("%.2fkm", store.distance)
        var remainStat = "충분"
        var count = "100개 이상"
        var color = Color.GREEN
        when (store.remain_stat) {
            "plenty" -> {
                remainStat = "충분"
                count = "100개 이상"
                color = Color.GREEN
            }
            "some" -> {
                remainStat = "여유"
                count = "30개 이상"
                color = Color.YELLOW
            }
            "few" -> {
                remainStat = "매진 임박"
                count = "2개 이상"
                color = Color.RED
            }
            "empty" -> {
                remainStat = "재고 없음"
                count = "1개 이하"
                color = Color.GRAY
            }
            else -> {}
        }
        holder.remainTextView.text = remainStat
        holder.countTextView.text = count
        holder.remainTextView.setTextColor(color)
        holder.countTextView.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}