package com.example.petcarevet.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.petcarevet.R
import com.example.petcarevet.databinding.FragmentProfileBinding
import com.example.petcarevet.di.ServiceLocator

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val role = ServiceLocator.sessionDataSource.getRole()
        binding.txtProfileRole.text = "Perfil activo: ${role.name}"

        binding.btnLogout.setOnClickListener {
            ServiceLocator.sessionDataSource.clear()
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
