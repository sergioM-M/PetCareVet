package com.example.petcarevet.ui.pets.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcarevet.data.local.session.UserRole
import com.example.petcarevet.databinding.FragmentPetsListBinding
import com.example.petcarevet.di.ServiceLocator
import androidx.navigation.fragment.findNavController

class PetsListFragment : Fragment() {

    private var _binding: FragmentPetsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PetsListViewModel by viewModels {
        PetsListViewModelFactory(
            ServiceLocator.getPetsUseCase,
            ServiceLocator.deletePetUseCase,
            ServiceLocator.deleteAllPetsUseCase,
            ServiceLocator.seedPetsUseCase,
            ServiceLocator.sessionDataSource.getRole()
        )
    }

    private lateinit var adapter: PetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPetsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PetAdapter(
            onClick = { pet ->
                val action = PetsListFragmentDirections.actionPetsListFragmentToPetDetailFragment(pet.id)
                findNavController().navigate(action)
            },
            onEdit = { pet ->
                val action = PetsListFragmentDirections.actionPetsListFragmentToPetFormFragment(pet.id)
                findNavController().navigate(action)
            },
            onDelete = { pet ->
                confirmDeletePet(pet.id, pet.name)
            }
        )

        binding.recyclerPets.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerPets.adapter = adapter

        val role = ServiceLocator.sessionDataSource.getRole()
        binding.txtRoleInfo.text = if (role == UserRole.ADMIN) {
            "Perfil administrador: CRUD completo y vaciado de BBDD disponible."
        } else {
            "Perfil usuario: CRUD completo disponible."
        }
        binding.btnDeleteAll.visibility = if (role == UserRole.ADMIN) View.VISIBLE else View.GONE

        binding.fabAddPet.setOnClickListener {
            val action = PetsListFragmentDirections.actionPetsListFragmentToPetFormFragment(-1)
            findNavController().navigate(action)
        }

        binding.btnDeleteAll.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Vaciar base de datos")
                .setMessage("Esta acción eliminará todas las mascotas.")
                .setPositiveButton("Vaciar") { _, _ -> viewModel.deleteAllPets() }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        viewModel.pets.observe(viewLifecycleOwner) { pets ->
            adapter.submitList(pets)
        }
    }

    private fun confirmDeletePet(id: Int, name: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar mascota")
            .setMessage("¿Quieres eliminar a $name?")
            .setPositiveButton("Eliminar") { _, _ -> viewModel.deletePet(id) }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
