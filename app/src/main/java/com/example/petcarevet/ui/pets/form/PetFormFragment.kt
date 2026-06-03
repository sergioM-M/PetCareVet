package com.example.petcarevet.ui.pets.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.petcarevet.R
import com.example.petcarevet.databinding.FragmentPetFormBinding
import com.example.petcarevet.di.ServiceLocator

class PetFormFragment : Fragment() {

    private var _binding: FragmentPetFormBinding? = null
    private val binding get() = _binding!!

    private val args: PetFormFragmentArgs by navArgs()

    private val viewModel: PetFormViewModel by viewModels {
        PetFormViewModelFactory(
            ServiceLocator.getPetByIdUseCase,
            ServiceLocator.addPetUseCase,
            ServiceLocator.updatePetUseCase
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPetFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val isEditMode = args.petId != -1
        binding.txtFormTitle.text = if (isEditMode) "Editar mascota" else "Nueva mascota"

        viewModel.loadPet(args.petId)

        viewModel.pet.observe(viewLifecycleOwner) { pet ->
            pet ?: return@observe
            binding.inputName.setText(pet.name)
            binding.inputCare.setText(pet.careDescription)
            binding.inputImageUrl.setText(pet.imageUrl)
            if (pet.type == "Gato") {
                binding.radioCat.isChecked = true
            } else {
                binding.radioDog.isChecked = true
            }
        }

        binding.btnSavePet.setOnClickListener {
            val type = when (binding.radioType.checkedRadioButtonId) {
                R.id.radioCat -> "Gato"
                R.id.radioDog -> "Perro"
                else -> ""
            }

            val valid = viewModel.savePet(
                id = args.petId,
                name = binding.inputName.text.toString(),
                type = type,
                careDescription = binding.inputCare.text.toString(),
                imageUrl = binding.inputImageUrl.text.toString()
            )

            if (!valid) {
                Toast.makeText(requireContext(), "Rellena todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.saved.observe(viewLifecycleOwner) { saved ->
            if (saved) findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
