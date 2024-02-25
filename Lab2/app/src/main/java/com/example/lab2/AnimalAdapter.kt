package com.example.lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import android.widget.Filterable
import java.util.Locale

class AnimalAdapter : ListAdapter<MyDataItem, AnimalAdapter.AnimalViewHolder>(AnimalDiffCallback()), Filterable {

    private var animalList: List<MyDataItem> = listOf()
    private var filteredList: List<MyDataItem> = listOf()

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val speciesTextView: TextView = itemView.findViewById(R.id.speciesTextView)
        val habitatTextView: TextView = itemView.findViewById(R.id.habitatTextView)
        val characteristicsTextView: TextView = itemView.findViewById(R.id.characteristicsTextView)
        val weightTextView: TextView = itemView.findViewById(R.id.weightTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = getItem(position)
        holder.nameTextView.text = animal.name
        holder.speciesTextView.text = animal.taxonomy.kingdom
        holder.habitatTextView.text = animal.taxonomy.`class`
        holder.characteristicsTextView.text = animal.taxonomy.order
        holder.weightTextView.text = animal.taxonomy.family
    }

    class AnimalDiffCallback : DiffUtil.ItemCallback<MyDataItem>() {
        override fun areItemsTheSame(oldItem: MyDataItem, newItem: MyDataItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MyDataItem, newItem: MyDataItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString().toLowerCase(Locale.getDefault())
                filteredList = if (charString.isEmpty()) {
                    animalList
                } else {
                    val filtered = mutableListOf<MyDataItem>()
                    for (animal in animalList) {
                        if (animal.name.toLowerCase(Locale.getDefault()).contains(charString)) {
                            filtered.add(animal)
                        }
                    }
                    filtered
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<MyDataItem>
                notifyDataSetChanged()
            }
        }
    }

    fun setData(animalList: List<MyDataItem>) {
        this.animalList = animalList
        this.filteredList = animalList
        notifyDataSetChanged()
    }


}
