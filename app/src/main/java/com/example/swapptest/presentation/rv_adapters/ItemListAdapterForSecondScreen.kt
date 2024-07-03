package com.example.swapptest.presentation.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swapptest.R
import com.example.swapptest.databinding.ItemSelectedCharactersFromFirstScreenBinding
import com.example.swapptest.domain.entity.Person

class ItemListAdapterForSecondScreen :
    ListAdapter<Person, ItemListAdapterForSecondScreen.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemSelectedCharactersFromFirstScreenBinding.bind(view)
        fun bind(person: Person) = with(binding) {
            screen2NameOfPersonFromApi.text = person.name
            screen2MaleFromApi.text = person.gender
            screen2DateOfBornFromApi.text = person.birth_year
        }
    }

    class Comparator : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemListAdapterForSecondScreen.Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_selected_characters_from_first_screen,
            parent,
            false
        )
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}