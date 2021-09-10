package com.azhar.pixeleditor.Model.Repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream

class EditImageRepositoryImpl(private val context: Context):EditImageRepository {
    override suspend fun prepareImagePreview(imageUri: Uri): Bitmap? {
        getInputStreamFromUri(imageUri)?.let{ inputStream ->
            val originalBitmap = BitmapFactory.decodeStream(inputStream as InputStream?)
            val width = context.resources.displayMetrics.widthPixels
            val height = ((originalBitmap.height*width)/originalBitmap.width)
            return Bitmap.createScaledBitmap(originalBitmap, width,  height,false)
        }?:return null
    }

    private fun getInputStreamFromUri(imageUri: Uri): InputStream? {
        return context.contentResolver.openInputStream(imageUri)
    }
}