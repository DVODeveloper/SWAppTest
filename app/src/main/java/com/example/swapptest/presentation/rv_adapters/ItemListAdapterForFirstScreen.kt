package com.example.swapptest.presentation.rv_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swapptest.R
import com.example.swapptest.data.database.MainDb
import com.example.swapptest.databinding.ItemForScreen1Binding
import com.example.swapptest.domain.entity.FilmItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemListAdapterForFirstScreen(
    val listener: FilmListener
) :
    ListAdapter<FilmItem, ItemListAdapterForFirstScreen.Holder>(Comparator()) {


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {


        private val binding = ItemForScreen1Binding.bind(view)
        fun bind(filmItem: FilmItem, listener: FilmListener) = with(binding) {
            screen1TitleOfFilmFromApi.text = filmItem.title
            screen1DirectorFromApi.text = filmItem.director
            screen1ProducerFromApi.text = filmItem.producer
            screen1YearOfProductionFromApi.text = filmItem.release_date

            itemView.setOnClickListener {
                listener.onClick(filmItem)
            }
//            CoroutineScope(Dispatchers.IO).launch {
//
//                val item = FilmItem(
//                    filmItem.title,
//                    filmItem.episode_id,
//                    filmItem.director,
//                    filmItem.producer,
//                    filmItem.release_date
//                )
//                db.getDao().insertFilmItem(item)
//            }
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
        holder.bind(getItem(position), listener)
    }

    override fun submitList(list: List<FilmItem>?) {
        super.submitList(list?.sortedBy { it.episode_id })
    }

    interface FilmListener {
        fun onClick(filmItem: FilmItem)
//        fun openCharacterList(characters: List<String>)
    }
}