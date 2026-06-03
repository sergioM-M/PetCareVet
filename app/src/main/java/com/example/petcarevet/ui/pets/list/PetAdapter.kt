package com.example.petcarevet.ui.pets.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcarevet.databinding.ItemPetBinding
import com.example.petcarevet.domain.model.Pet

class PetAdapter(
    private val onClick: (Pet) -> Unit,
    private val onEdit: (Pet) -> Unit,
    private val onDelete: (Pet) -> Unit
) : RecyclerView.Adapter<PetViewHolder>() {

    private var pets: List<Pet> = emptyList()

    fun submitList(newPets: List<Pet>) {
        pets = newPets
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = ItemPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding, onClick, onEdit, onDelete)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.renderize(pets[position])
    }

    override fun getItemCount(): Int = pets.size
}
