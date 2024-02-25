package com.example.lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter : ListAdapter<AnimalItem, AnimalAdapter.AnimalViewHolder>(AnimalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animalItem = getItem(position)
        holder.bind(animalItem)
    }

    fun submitAnimalList(animalList: List<AnimalItem>) {
        submitList(animalList)
    }


    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val habitatTextView: TextView = itemView.findViewById(R.id.habitatTextView)
        private val dietTextView: TextView = itemView.findViewById(R.id.dietTextView)
        private val lifespanTextView: TextView = itemView.findViewById(R.id.lifespanTextView)
        private val predatorsTextView: TextView = itemView.findViewById(R.id.predatorsTextView)

        fun bind(animalItem: AnimalItem) {
            val characteristics = animalItem.characteristics
            nameTextView.text = animalItem.name
            habitatTextView.text = "Habitat: ${characteristics.habitat}"
            dietTextView.text = "Diet: ${characteristics.diet}"
            lifespanTextView.text = "Lifespan: ${characteristics.lifespan}"
            predatorsTextView.text = "Predators: ${characteristics.predators}"
        }
    }

    class AnimalDiffCallback : DiffUtil.ItemCallback<AnimalItem>() {
        override fun areItemsTheSame(oldItem: AnimalItem, newItem: AnimalItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: AnimalItem, newItem: AnimalItem): Boolean {
            return oldItem == newItem
        }
    }
}
