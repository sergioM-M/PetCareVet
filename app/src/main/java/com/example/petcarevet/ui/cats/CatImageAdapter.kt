package com.example.petcarevet.ui.cats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petcarevet.databinding.ItemCatImageBinding
import com.example.petcarevet.domain.model.CatImage

class CatImageAdapter : RecyclerView.Adapter<CatImageViewHolder>() {

    private var images: List<CatImage> = emptyList()

    fun submitList(newImages: List<CatImage>) {
        images = newImages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatImageViewHolder {
        val binding = ItemCatImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatImageViewHolder, position: Int) {
        holder.renderize(images[position])
    }

    override fun getItemCount(): Int = images.size
}
