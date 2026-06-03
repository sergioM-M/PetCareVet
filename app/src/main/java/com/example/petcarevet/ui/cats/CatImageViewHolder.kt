package com.example.petcarevet.ui.cats

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petcarevet.databinding.ItemCatImageBinding
import com.example.petcarevet.domain.model.CatImage

class CatImageViewHolder(
    private val binding: ItemCatImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun renderize(catImage: CatImage) {
        Glide.with(binding.imgCat)
            .load(catImage.url)
            .centerCrop()
            .into(binding.imgCat)
    }
}
