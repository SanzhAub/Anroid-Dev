package com.example.lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.recyclerview.widget.DiffUtil


class AnimalAdapter(private var animalList: List<AnimalItem>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val habitatTextView: TextView = itemView.findViewById(R.id.habitatTextView)
        val dietTextView: TextView = itemView.findViewById(R.id.dietTextView)
        val lifespanTextView: TextView = itemView.findViewById(R.id.lifespanTextView)
        val topSpeedTextView: TextView = itemView.findViewById(R.id.topSpeedTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val currentItem = animalList[position]
        holder.nameTextView.text = currentItem.name
        holder.habitatTextView.text = currentItem.characteristics.habitat
        holder.dietTextView.text = currentItem.characteristics.diet
        holder.lifespanTextView.text = currentItem.characteristics.lifespan
        holder.topSpeedTextView.text = currentItem.characteristics.top_speed
    }

    override fun getItemCount() = animalList.size

    fun updateData(newList: List<AnimalItem>) {
        val diffResult = DiffUtil.calculateDiff(AnimalDiffCallback(animalList, newList))
        animalList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class AnimalDiffCallback(private val oldList: List<AnimalItem>, private val newList: List<AnimalItem>) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]
    }
}
