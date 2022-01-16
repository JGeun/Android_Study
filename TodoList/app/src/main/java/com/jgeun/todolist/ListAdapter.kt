package com.jgeun.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.jgeun.todolist.databinding.ItemListBinding

class ListAdapter(private val context: Context, private val dataList: ArrayList<ToDoData>) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    class ViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        val itemView : ConstraintLayout = binding.root
        val title = binding.title
        val contents = binding.contents
        val done = binding.done
        val cancel = binding.delete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.contents.text = dataList[position].contents
        if(!dataList[position].isDone){
            holder.done.visibility = View.VISIBLE
        }
        else {
            holder.done.visibility = View.INVISIBLE
            holder.itemView.background =
                ResourcesCompat.getDrawable(context.resources, R.drawable.bg_item_list_done, null)
        }

        holder.done.setOnClickListener{
            dataList[position].isDone = true
            notifyDataSetChanged()
        }

        holder.cancel.setOnClickListener{
            dataList.removeAt(position)
            holder.itemView.background =
                    ResourcesCompat.getDrawable(context.resources, R.drawable.bg_item_list, null)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = dataList.size
}