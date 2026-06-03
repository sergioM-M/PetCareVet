package com.example.petcarevet.ui.pets.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.petcarevet.databinding.FragmentPetDetailBinding
import com.example.petcarevet.di.ServiceLocator

class PetDetailFragment : Fragment() {

    private var _binding: FragmentPetDetailBinding? = null
    private val binding get() = _binding!!

    private val args: PetDetailFragmentArgs by navArgs()

    private val viewModel: PetDetailViewModel by viewModels {
        PetDetailViewModelFactory(ServiceLocator.getPetByIdUseCase)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadPet(args.petId)

        binding.btnEditFromDetail.setOnClickListener {
            val action = PetDetailFragmentDirections.actionPetDetailFragmentToPetFormFragment(args.petId)
            findNavController().navigate(action)
        }

        viewModel.pet.observe(viewLifecycleOwner) { pet ->
            if (pet == null) {
                findNavController().popBackStack()
                return@observe
            }

            binding.txtDetailName.text = pet.name
            binding.txtDetailType.text = pet.type
            binding.txtDetailCare.text = pet.careDescription

            Glide.with(binding.imgDetailPet)
                .load(pet.imageUrl)
                .centerCrop()
                .into(binding.imgDetailPet)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
