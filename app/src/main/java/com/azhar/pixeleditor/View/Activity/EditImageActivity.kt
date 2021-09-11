package com.azhar.pixeleditor.View.Activity

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.azhar.pixeleditor.View.Adapter.ImageFiltersAdapter
import com.azhar.pixeleditor.View.Utility.displayToast
import com.azhar.pixeleditor.View.Utility.show
import com.azhar.pixeleditor.ViewModel.EditImageViewModel
import com.azhar.pixeleditor.databinding.ActivityEditImageBinding
import org.koin.android.viewmodel.ext.android.viewModel

class EditImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditImageBinding

    private val viewModel:EditImageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        prepareImagePreview()
        setupObaserver()
    }

    private fun setupObaserver() {
        viewModel.imagePreviewUiState.observe(this,{
            val dataState = it?:return@observe
            binding.previewProgressBar.visibility =
                if (dataState.isLoading) View.VISIBLE else View.GONE
            dataState.bitmap?.let { bitmap ->
                binding.imagePreview.setImageBitmap(bitmap)
                binding.imagePreview.show()
                viewModel.loadImageFilters(bitmap)
            }?:kotlin.run {
                dataState.error?.let { error ->
                    displayToast(error)
                }
            }
        })

        viewModel.imageFiltersUiState.observe(this,{
            val imageFiltersDataState = it ?: return@observe
            binding.imageFiltersProgressBar.visibility =
                if(imageFiltersDataState.isLoading)View.VISIBLE else View.GONE
            imageFiltersDataState.imageFilters?.let { imageFilters ->
                ImageFiltersAdapter(imageFilters).also { adapter ->
                    binding.filterRecyclerView.adapter = adapter
                }
            }?: kotlin.run {
                imageFiltersDataState.error?.let { error ->
                    displayToast(error)
                }
            }
        })
    }

    private fun prepareImagePreview() {
        intent.getParcelableExtra<Uri>(MainActivity.KEY_IMAGE_URI)?.let { imageUri ->
            viewModel.prepareImagePreview(imageUri)
        }
    }


    private fun setListeners() {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }
}