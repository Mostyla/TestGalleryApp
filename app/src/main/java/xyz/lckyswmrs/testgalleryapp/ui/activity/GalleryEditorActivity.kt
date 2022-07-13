package xyz.lckyswmrs.testgalleryapp.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import xyz.lckyswmrs.testgalleryapp.R
import xyz.lckyswmrs.testgalleryapp.databinding.ActivityGalleryEditorBinding
import xyz.lckyswmrs.testgalleryapp.model.ImageStorageManager
import xyz.lckyswmrs.testgalleryapp.model.ImageStorageManager.Companion.OnDeleteFileListener
import xyz.lckyswmrs.testgalleryapp.model.PATH_TO_SAVE_IMAGES
import xyz.lckyswmrs.testgalleryapp.preferences.AppPreferences
import xyz.lckyswmrs.testgalleryapp.ui.activity.GalleryActivity.Companion.IMG
import java.io.File
import java.io.FileOutputStream

class GalleryEditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryEditorBinding
    private var imageUri: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        initTollBarButtons()

        initImage()

        initButtons()
    }

    private fun initTollBarButtons() {

        binding.imageBtnCrown.setOnClickListener {
            Toast.makeText(this, getString(R.string.click), Toast.LENGTH_SHORT).show()
        }

        binding.imageBtnBack.setOnClickListener {
            finish()
        }
    }

    private fun getData() {
        imageUri = intent.getStringExtra(IMG) ?: ""
    }

    private fun initImage() {
        Glide.with(this)
            .load(imageUri)
            .into(binding.loadedPhotoImageView)

        Log.d("IMG", imageUri)
    }

    private fun initButtons() {

        val imageName = getImageName(imageUri)

        binding.btnSave.isVisible = !AppPreferences(applicationContext).getSavedImage(imageName)


        binding.btnSave.setOnClickListener {
            Log.d("imageSave", imageName)
            ImageStorageManager.saveToInternalStorage(
                binding.loadedPhotoImageView.drawable.toBitmap(),
                applicationContext,
                binding.btnSave,
                imageName
            )

        }

        binding.btnDelete.setOnClickListener {
            val file = Environment.getExternalStorageDirectory()
            val path = File(file.absolutePath + PATH_TO_SAVE_IMAGES)
            val outFile = File(path, imageName)
            Log.d("CheckPath", outFile.toString())
            ImageStorageManager.deleteFile(this, outFile, object : OnDeleteFileListener {
                override fun onDeleteSuccess() {
                    AppPreferences(applicationContext).setSavedImage(imageName, false)
                }

                override fun onDeleteError() {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.delete_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
            binding.btnSave.visibility = View.VISIBLE
        }


        binding.btnShare.setOnClickListener {
            shareImage()
        }

    }


    private fun getImageName(imagePath: String): String {

        val imagePathArray = imagePath.split("/")
        val imagePathSize = imagePathArray.size

        return imagePathArray[imagePathSize - 1]
    }

    private fun shareImage() {

        val bitmapImg: Bitmap = binding.loadedPhotoImageView.drawable.toBitmap()
        val root = Environment.getExternalStorageDirectory()
        val imagePath = File(root.absolutePath + SHARE_IMG_PATH)
        try {
            imagePath.createNewFile()
            val oStream = FileOutputStream(imagePath)
            bitmapImg.compress(CompressFormat.PNG, 100, oStream)
            oStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val shareImage = Intent(Intent.ACTION_SEND)
        shareImage.type = SHARE_TYPE
        shareImage.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imagePath))
        startActivity(Intent.createChooser(shareImage, getString(R.string.share)))
    }

    companion object {
        const val SHARE_IMG_PATH = "/DCIM/Camera/image.png"
        const val SHARE_TYPE = "image/*"
    }

}
