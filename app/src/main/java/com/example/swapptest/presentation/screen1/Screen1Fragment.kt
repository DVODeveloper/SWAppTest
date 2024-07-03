package com.example.swapptest.presentation.screen1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.swapptest.data.api.RetrofitInstance
import com.example.swapptest.databinding.FragmentScreen1Binding
import com.example.swapptest.domain.entity.FilmItem
import com.example.swapptest.presentation.rv_adapters.ItemListAdapterForFirstScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

class Screen1Fragment : Fragment(), ItemListAdapterForFirstScreen.Listener{

    private lateinit var firstScreenAdapter: ItemListAdapterForFirstScreen
    private var _binding: FragmentScreen1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen1Binding.inflate(inflater, container, false)
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

            withContext(Dispatchers.Main) {
                responseList?.results?.let {
                    firstScreenAdapter.submitList(it)
                }
            }
        }

    }


    override fun onClick(filmItem: FilmItem) {
        Toast.makeText(requireContext(), "Нажали на: ${filmItem.title}", Toast.LENGTH_LONG)
            .show()
        Log.d("Screen1Fragment", filmItem.title.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Screen1Fragment()
    }
}