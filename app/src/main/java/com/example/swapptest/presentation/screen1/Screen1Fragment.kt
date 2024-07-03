package com.example.swapptest.presentation.screen1

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swapptest.data.api.RetrofitInstance
import com.example.swapptest.databinding.FragmentScreen1Binding
import com.example.swapptest.domain.entity.FilmItem
import com.example.swapptest.presentation.rv_adapters.ItemListAdapterForFirstScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Screen1Fragment : Fragment() {

    private lateinit var firstScreenAdapter: ItemListAdapterForFirstScreen

    var _binding: FragmentScreen1Binding? = null
    private val binding: FragmentScreen1Binding
        get() = _binding ?: throw RuntimeException("FragmentScreen1Binding == null")



    private val viewModel: Screen1ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentScreen1Binding.inflate(inflater,container,false)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter(){
        firstScreenAdapter = ItemListAdapterForFirstScreen()
        _binding?.rvItemsForFirstScreen?.adapter = firstScreenAdapter

        CoroutineScope(Dispatchers.IO).launch {

                val responseList = RetrofitInstance.apiService.getFilmList()

                withContext(Dispatchers.Main){
                    _binding.apply {
                        firstScreenAdapter.submitList(responseList.results)
                    }
                }
        }
    }


    companion object {
        fun newInstance() = Screen1Fragment()
    }
}