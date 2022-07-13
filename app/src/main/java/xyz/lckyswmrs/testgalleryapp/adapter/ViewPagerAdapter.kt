package xyz.lckyswmrs.testgalleryapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import xyz.lckyswmrs.testgalleryapp.ui.fragments.PhotosFragment
import xyz.lckyswmrs.testgalleryapp.ui.fragments.VideosFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PhotosFragment()
            1 -> VideosFragment()
            else -> PhotosFragment()
        }
    }
}