package xyz.lckyswmrs.testgalleryapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import xyz.lckyswmrs.testgalleryapp.R
import xyz.lckyswmrs.testgalleryapp.adapter.ViewPagerAdapter
import xyz.lckyswmrs.testgalleryapp.databinding.ActivityGalleryBinding


class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private val tabTitle = arrayOf(
      "Photos", "Videos"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBarButtons()
        initViewPagerAndTabLayout()

    }

    private fun initViewPagerAndTabLayout(){
        binding.galleryViewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.galleryTabLayout, binding.galleryViewPager){
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun initToolBarButtons(){

        binding.imageBackBtn.setOnClickListener {
            finish()
        }

        binding.imageBtnCrown.setOnClickListener {
            Toast.makeText(this@GalleryActivity,getString(R.string.click),Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val IMG = "img"
    }


}