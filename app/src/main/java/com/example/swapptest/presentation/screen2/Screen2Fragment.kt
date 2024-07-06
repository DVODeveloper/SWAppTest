package com.example.swapptest.presentation.screen2

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.swapptest.R
import com.example.swapptest.data.api.RetrofitInstance
import com.example.swapptest.databinding.FragmentScreen2Binding
import com.example.swapptest.domain.entity.Character
import com.example.swapptest.domain.entity.CharacterResponse
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

    private lateinit var personResult: List<Character>

    private val viewModel: Screen2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen2Binding.inflate(inflater, container, false)
//        setupAdapter()
        return binding.root
    }
//
//    private fun parseArgs() {
//        requireArguments().getStringArrayList(KEY1)?.let {
//            personResult = it.mapNotNull { extractCharacterId(it) }
//        }
//    }

//    private fun setupAdapter() {
//        secondScreenAdapter = ItemListAdapterForSecondScreen()
//        binding.rvItemsForSecondScreen.adapter = secondScreenAdapter
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                withContext(Dispatchers.Main) {
//                    secondScreenAdapter.submitList(personResult)
//                }
//            } catch (e: Exception) {
//                Log.e("Screen2Fragment", "Error fetching film list", e)
//            }
//        }
//    }

    companion object {

        private const val KEY1 = "PersonsList"
        fun newInstance() = Screen2Fragment()

        fun newInstanceAddPersonsListFromSelectedFilm(listResult: List<String>): Screen2Fragment {
            return Screen2Fragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(KEY1, ArrayList(listResult))
                }
            }
        }

        fun goToScreen2Fragment(findNavController: NavController) {
            findNavController.navigate(R.id.action_screen1Fragment_to_screen2Fragment)

//            Toast.makeText(requireContext(), "Перешли на второй фрагмент", Toast.LENGTH_LONG).show()
        }
    }
}


