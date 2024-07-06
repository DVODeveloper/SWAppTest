package com.example.swapptest.presentation.screen1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.swapptest.data.api.RetrofitInstance
import com.example.swapptest.data.database.MainDb
import com.example.swapptest.databinding.FragmentScreen1Binding
import com.example.swapptest.domain.entity.FilmItem
import com.example.swapptest.presentation.rv_adapters.ItemListAdapterForFirstScreen
import com.example.swapptest.presentation.screen2.Screen2Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

class Screen1Fragment : Fragment(), ItemListAdapterForFirstScreen.FilmListener {

    private lateinit var db: MainDb

    private lateinit var firstScreenAdapter: ItemListAdapterForFirstScreen
    private var _binding: FragmentScreen1Binding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen1Binding.inflate(inflater, container, false)

        db = MainDb.getDb(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        firstScreenAdapter = ItemListAdapterForFirstScreen(this)
        binding.rvItemsForFirstScreen.adapter = firstScreenAdapter

        CoroutineScope(Dispatchers.IO).launch {
            val responseList = withTimeoutOrNull(60000) {
                RetrofitInstance.apiService.getFilmList()
            }

            responseList?.results?.let { filmItems ->

                withContext(Dispatchers.Main) {
                    firstScreenAdapter.submitList(filmItems)
                }
            }
        }
    }

    override fun onClick(filmItem: FilmItem) {
        Toast.makeText(requireContext(), "Нажали на: ${filmItem.title}", Toast.LENGTH_LONG).show()
        Log.d("Screen1Fragment", filmItem.title.toString())
        Screen2Fragment.goToScreen2Fragment(findNavController())

    }

//    private fun testDB(filmItem: FilmItem) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val item = FilmItem(
//                filmItem.title,
//                filmItem.episode_id,
//                filmItem.director,
//                filmItem.producer,
//                filmItem.release_date
//            )
//            db.getDao().insertFilmItem(item)
//        }
//
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Screen1Fragment()
    }
}