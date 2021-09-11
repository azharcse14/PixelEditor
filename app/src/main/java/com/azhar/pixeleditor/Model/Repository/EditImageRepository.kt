package com.azhar.pixeleditor.Model.Repository

import android.graphics.Bitmap
import android.net.Uri
import com.azhar.pixeleditor.Model.Data.ImageFilter

interface EditImageRepository {

    suspend fun prepareImagePreview(imageUri:Uri):Bitmap?
    suspend fun getImageFilters(image:Bitmap): List<ImageFilter>
}