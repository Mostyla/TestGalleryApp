package xyz.lckyswmrs.testgalleryapp.model

import android.content.Context
import android.provider.MediaStore
import java.util.*

 class ItemGalleryModel() {

    companion object {

        fun findImagesFiles(context: Context): List<String> {
            val fileList: MutableList<String> = ArrayList()
            val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
            val orderBy = MediaStore.Images.Media._ID
            val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy
            )
            if (cursor != null) {
                val count = cursor.count
                val arrPath = arrayOfNulls<String>(count)
                for (i in 0 until count) {
                    cursor.moveToPosition(i)
                    val dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                    arrPath[i] = cursor.getString(dataColumnIndex)
                    arrPath[i]?.let { fileList.add(it) }
                }
                cursor.close()
            }
            return fileList
        }

        fun findVideosFiles(context: Context): List<String> {
            val fileList: MutableList<String> = ArrayList()
            val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
            val orderBy = MediaStore.Images.Media._ID
            val cursor = context.contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy
            )
            if (cursor != null) {
                val count = cursor.count
                val arrPath = arrayOfNulls<String>(count)
                for (i in 0 until count) {
                    cursor.moveToPosition(i)
                    val dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                    arrPath[i] = cursor.getString(dataColumnIndex)
                    arrPath[i]?.let { fileList.add(it) }
                }
                cursor.close()
            }
            return fileList
        }

    }
}