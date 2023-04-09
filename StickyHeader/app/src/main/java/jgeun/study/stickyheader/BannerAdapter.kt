package jgeun.study.stickyheader

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.stickyheader.databinding.ItemBannerBinding

class BannerAdapter(
	private val context: Context
) : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

	private val colorDataSet = arrayListOf<Int>(
		getColor(R.color.purple_500),
		getColor(R.color.teal_200),
		getColor(R.color.purple_200),
		getColor(R.color.teal_700),
	)

	class ViewHolder(private val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(colorRes: Int) {
			binding.bannerPositonText.text = "$adapterPosition"
			binding.bannerLayout.setBackgroundColor(colorRes)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding = ItemBannerBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return ViewHolder(binding)
	}

	override fun getItemCount(): Int = colorDataSet.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(colorDataSet[position])
	}

	private fun getColor(color: Int)
		= ResourcesCompat.getColor(context.resources, color, null)
}