package com.example.swapptest.presentation.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swapptest.R
import com.example.swapptest.databinding.ItemForScreen1Binding
import com.example.swapptest.domain.entity.FilmItem
import com.example.swapptest.domain.entity.Person

class ItemListAdapterForFirstScreen :
    ListAdapter<FilmItem, ItemListAdapterForFirstScreen.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemForScreen1Binding.bind(view)
        fun bind(filmItem: FilmItem) = with(binding) {
            screen1TitleOfFilmFromApi.text = filmItem.title
            screen1DirectorFromApi.text = filmItem.director
            screen1ProducerFromApi.text = filmItem.producer
            screen1YearOfProductionFromApi.text = filmItem.release_date
        }

    }
    class Comparator : DiffUtil.ItemCallback<FilmItem>() {
        override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
            return oldItem.episode_id == newItem.episode_id
        }
        override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_for_screen1,
            parent,
            false
        )
        return Holder(view)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))

    }

    override fun submitList(list: List<FilmItem>?) {
        super.submitList(list?.sortedBy { it.episode_id })
    }
}