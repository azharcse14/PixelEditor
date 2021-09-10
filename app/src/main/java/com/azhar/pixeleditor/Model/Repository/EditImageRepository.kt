package com.azhar.pixeleditor.Model.Repository

import android.graphics.Bitmap
import android.net.Uri

interface EditImageRepository {
    suspend fun prepareImagePreview(imageUri:Uri):Bitmap?
}