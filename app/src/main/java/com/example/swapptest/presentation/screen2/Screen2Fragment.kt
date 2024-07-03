package com.example.swapptest.presentation.screen2

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swapptest.data.api.RetrofitInstance
import com.example.swapptest.databinding.FragmentScreen2Binding
import com.example.swapptest.domain.entity.Person
import com.example.swapptest.presentation.rv_adapters.ItemListAdapterForSecondScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Screen2Fragment : Fragment() {

    lateinit var secondScreenAdapter: ItemListAdapterForSecondScreen
    var _binding: FragmentScreen2Binding? = null
    private val binding: FragmentScreen2Binding
        get() = _binding ?: throw RuntimeException("FragmentScreen2Binding == null")

    private lateinit var personResult: Person


    private val viewModel: Screen2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parseArgs()
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen2Binding.inflate(inflater, container, false)
//        setupAdapter()
        return binding.root
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Person>(KEY1)?.let {
            personResult = it
        }
    }

    companion object {

        private const val KEY1 = "PersonsList"
        fun newInstance() = Screen2Fragment()

        fun newInstanceAddPersonsListFromSelectedFilm(personResult: Person): Screen2Fragment {
            return Screen2Fragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY1, personResult)
                }
            }

        }

    }

//    private fun setupAdapter() {
//        secondScreenAdapter = ItemListAdapterForSecondScreen()
//        _binding?.rvItemsForSecondScreen?.adapter = secondScreenAdapter
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val filmListResult = RetrofitInstance.apiService.getFilmList()
//                val characters = filmListResult.results.first().characters
//
////                withContext(Dispatchers.Main) {
////                    secondScreenAdapter.submitList(characters)
////                }
//            } catch (e: Exception) {
//                Log.e("Screen2Fragment", "Error fetching film list", e)
//
//            }
//        }
//    }
}