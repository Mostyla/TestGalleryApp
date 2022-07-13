package xyz.lckyswmrs.testgalleryapp.ui.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import xyz.lckyswmrs.testgalleryapp.R
import xyz.lckyswmrs.testgalleryapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initHeaderBtn()
        checkUserPermission()
    }


    private fun initHeaderBtn() {

        binding.imageBtnMenu.setOnClickListener {
            Toast.makeText(this@MainActivity, getString(R.string.click), Toast.LENGTH_SHORT).show()
        }

        binding.imageBtnCrown.setOnClickListener {
            Toast.makeText(this@MainActivity, getString(R.string.click), Toast.LENGTH_SHORT).show()
        }
    }


    private fun checkUserPermission() {

        binding.galleryTextView.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    Array(1) { android.Manifest.permission.READ_EXTERNAL_STORAGE },
                    REQUEST_CODE
                )

                return@setOnClickListener
            }

                galleryScreen()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            galleryScreen()
        } else {
            showDialog(this)
        }
    }

    private fun galleryScreen(){

        startActivity(Intent(this@MainActivity, GalleryActivity::class.java))

    }


    private fun showDialog(context: Context) {

        val customDialog = Dialog(context)
        customDialog.setContentView(R.layout.dialog_gallery_permission)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val startImageView = customDialog.findViewById<ImageView>(R.id.startBtnImageView)
        val cancelTextView = customDialog.findViewById<TextView>(R.id.cancelTextView)
        startImageView.setOnClickListener {
            checkUserPermission()
            customDialog.dismiss()
        }
        cancelTextView.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.window!!.decorView.setBackgroundColor(Color.TRANSPARENT)
        customDialog.show()
    }

    companion object {
        const val REQUEST_CODE = 121
    }

}

