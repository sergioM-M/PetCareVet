package com.example.petcarevet.ui.pets.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petcarevet.databinding.ItemPetBinding
import com.example.petcarevet.domain.model.Pet

class PetViewHolder(
    private val binding: ItemPetBinding,
    private val onClick: (Pet) -> Unit,
    private val onEdit: (Pet) -> Unit,
    private val onDelete: (Pet) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun renderize(pet: Pet) {
        binding.txtPetName.text = pet.name
        binding.txtPetType.text = pet.type
        binding.txtPetCare.text = pet.careDescription

        Glide.with(binding.imgPet)
            .load(pet.imageUrl)
            .centerCrop()
            .into(binding.imgPet)

        binding.containerItem.setOnClickListener { onClick(pet) }
        binding.btnEditPet.setOnClickListener { onEdit(pet) }
        binding.btnDeletePet.setOnClickListener { onDelete(pet) }
    }
}
