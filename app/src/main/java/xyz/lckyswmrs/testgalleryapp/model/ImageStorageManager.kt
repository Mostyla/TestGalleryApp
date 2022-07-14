package xyz.lckyswmrs.testgalleryapp.model

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import xyz.lckyswmrs.testgalleryapp.R
import xyz.lckyswmrs.testgalleryapp.preferences.AppPreferences
import java.io.File
import java.io.FileOutputStream


const val PATH_TO_SAVE_IMAGES = "/MyGallery"

class ImageStorageManager {

    companion object {



        fun saveToInternalStorage(bitmap: Bitmap, context: Context, view: View, fileName: String) {

            var outputStream: FileOutputStream? = null
            val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val path = File(file.absolutePath + PATH_TO_SAVE_IMAGES)
            path.mkdirs()
            val filename = String.format(fileName)
            try {
                val outFile = File(path, filename)
                outputStream = FileOutputStream(outFile)
                Toast.makeText(context, context.getString(R.string.image_saved), Toast.LENGTH_SHORT)
                    .show()
                AppPreferences(context).setSavedImage(fileName,true)
                view.visibility = View.GONE
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
            }
            Log.d("check", outputStream.toString())
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream!!)
            try {
                outputStream!!.flush()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
            }
            try {
                outputStream!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
            }

        }

        fun deleteFile(activity: Activity, file: File, onDeleteFileListener: OnDeleteFileListener) {

            try {
                file.delete()
                if (file.exists()) {
                    file.canonicalFile.delete()
                    if (file.exists()) {
                        activity.applicationContext.deleteFile(file.name)
                        if (file.exists())
                            onDeleteFileListener.onDeleteSuccess()
                        else
                            onDeleteFileListener.onDeleteError()
                    } else {
                        onDeleteFileListener.onDeleteSuccess()
                    }
                } else {
                    onDeleteFileListener.onDeleteSuccess()
                }
            } catch (e: Exception) {
                onDeleteFileListener.onDeleteError()
            }

        }


        interface OnDeleteFileListener {

            fun onDeleteSuccess()
            fun onDeleteError()

        }


    }

}





