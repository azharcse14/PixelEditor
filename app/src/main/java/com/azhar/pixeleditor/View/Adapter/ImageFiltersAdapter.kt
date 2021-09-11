package com.azhar.pixeleditor.View.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azhar.pixeleditor.Model.Data.ImageFilter
import com.azhar.pixeleditor.databinding.ItemConteinerFilterBinding

class ImageFiltersAdapter(private val imageFilters: List<ImageFilter>):
    RecyclerView.Adapter<ImageFiltersAdapter.ImageFilterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageFilterViewHolder {
        val binding = ItemConteinerFilterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ImageFilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageFilterViewHolder, position: Int) {
        with(holder){
            with(imageFilters[position]){
                binding.imageFilterPreview.setImageBitmap(filterPreview)
                binding.textFilterName.text = name
            }
        }
    }

    override fun getItemCount(): Int {
        return imageFilters.size
    }

    inner class ImageFilterViewHolder(val binding : ItemConteinerFilterBinding): RecyclerView.ViewHolder(binding.root)

}