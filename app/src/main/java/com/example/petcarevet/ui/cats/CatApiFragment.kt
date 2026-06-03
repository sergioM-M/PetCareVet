package com.example.petcarevet.ui.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcarevet.databinding.FragmentCatApiBinding
import com.example.petcarevet.di.ServiceLocator

class CatApiFragment : Fragment() {

    private var _binding: FragmentCatApiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatApiViewModel by viewModels {
        CatApiViewModelFactory(ServiceLocator.getCatImagesUseCase)
    }

    private lateinit var adapter: CatImageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCatApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CatImageAdapter()
        binding.recyclerCats.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCats.adapter = adapter

        binding.btnSearchCats.setOnClickListener {
            viewModel.searchCats(binding.inputCatLimit.text.toString())
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                CatApiUiState.Idle -> {
                    binding.txtCatStatus.text = "Introduce un número y pulsa buscar."
                }
                CatApiUiState.Loading -> {
                    binding.txtCatStatus.text = "Cargando imágenes..."
                }
                is CatApiUiState.Success -> {
                    binding.txtCatStatus.text = "Imágenes cargadas: ${state.images.size}"
                    adapter.submitList(state.images)
                }
                is CatApiUiState.Error -> {
                    binding.txtCatStatus.text = state.message
                    adapter.submitList(emptyList())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
