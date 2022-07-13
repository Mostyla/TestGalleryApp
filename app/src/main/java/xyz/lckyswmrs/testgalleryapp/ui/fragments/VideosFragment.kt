package xyz.lckyswmrs.testgalleryapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import xyz.lckyswmrs.testgalleryapp.adapter.GalleryAdapter
import xyz.lckyswmrs.testgalleryapp.databinding.FragmentVideosBinding
import xyz.lckyswmrs.testgalleryapp.model.ItemGalleryModel
import xyz.lckyswmrs.testgalleryapp.ui.activity.GalleryActivity
import xyz.lckyswmrs.testgalleryapp.ui.activity.GalleryEditorActivity


class VideosFragment : Fragment(), GalleryAdapter.OnGalleryItemClickListener {

    private lateinit var binding: FragmentVideosBinding
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVideosBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.videosRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        val videoFiles: List<String> = ItemGalleryModel.findVideosFiles(requireContext())

        binding.videosRecyclerView.adapter =
            GalleryAdapter(requireActivity(), videoFiles , this)
    }

    override fun onItemCLicked(videoUri: String) {
        val intent = Intent(requireActivity(), GalleryEditorActivity::class.java)
        intent.putExtra(GalleryActivity.IMG, videoUri)
        startActivity(intent)
    }


}