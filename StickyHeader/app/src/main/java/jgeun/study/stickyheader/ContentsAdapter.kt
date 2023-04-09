package jgeun.study.stickyheader

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.stickyheader.databinding.ItemContentsBinding
import kotlin.random.Random

class ContentsAdapter : RecyclerView.Adapter<ContentsAdapter.ViewHolder>(){

	private val dataSet = arrayListOf<String>()

	init {
		for (i in 0 until 200) {
			dataSet.add("Contents: $i")
		}
	}

	inner class ViewHolder(private val binding: ItemContentsBinding) : RecyclerView.ViewHolder(binding.root){

		fun bind(contents: String) {
			binding.itemContentsLayout.setBackgroundColor(getRandomColor())
			binding.itemContentsText.text = contents
		}
	}

	override fun onViewRecycled(holder: ViewHolder) {
		super.onViewRecycled(holder)
		Log.d("Check@@@", "Check $holder")
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding = ItemContentsBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)

		Log.d("StickyHeader", "OnCreateViewHolder: $viewType")
		return ViewHolder(binding)
	}

	override fun getItemCount(): Int = dataSet.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(dataSet[position])
	}

	private fun getRandomColor(): Int {

		return when(Random.nextInt(5)) {
			0 -> Color.GREEN
			1 -> Color.RED
			2 -> R.color.teal_200
			3 -> Color.YELLOW
			else -> Color.CYAN
		}
	}
}