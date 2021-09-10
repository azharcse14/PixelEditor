package com.azhar.pixeleditor.ViewModel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azhar.pixeleditor.Model.Repository.EditImageRepository
import com.azhar.pixeleditor.View.Utility.Coroutines

class EditImageViewModel(private val editImageRepository:EditImageRepository): ViewModel() {

    private val imagePreviewDataState = MutableLiveData<ImagePreviewDataState>()
    val imagePreviewUiState: LiveData<ImagePreviewDataState>get() = imagePreviewDataState

    fun prepareImagePreview(imagUri: Uri){
        Coroutines.io {
            kotlin.runCatching {
                emitImagePreviewUiState(isLoading = true)
                editImageRepository.prepareImagePreview(imagUri)
            }.onSuccess { bitmap ->
                if (bitmap != null){
                    emitImagePreviewUiState(bitmap = bitmap)
                }else{
                    emitImagePreviewUiState(error = "Unable to prepare image preview")
                }
            }.onFailure {
                emitImagePreviewUiState(error = it.message.toString())
            }
        }
    }

    private fun emitImagePreviewUiState(
        isLoading: Boolean = false,
        bitmap: Bitmap? = null,
        error: String? = null
    ) {
        val dataState = ImagePreviewDataState(isLoading, bitmap, error)
        imagePreviewDataState.postValue(dataState)
    }

    data class ImagePreviewDataState (
        val isLoading:Boolean,
        val bitmap: Bitmap?,
        val error:String?
    )
}


