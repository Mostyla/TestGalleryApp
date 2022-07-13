package xyz.lckyswmrs.testgalleryapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import xyz.lckyswmrs.testgalleryapp.adapter.GalleryAdapter
import xyz.lckyswmrs.testgalleryapp.databinding.FragmentPhotosBinding
import xyz.lckyswmrs.testgalleryapp.model.ItemGalleryModel
import xyz.lckyswmrs.testgalleryapp.ui.activity.GalleryActivity
import xyz.lckyswmrs.testgalleryapp.ui.activity.GalleryEditorActivity

class PhotosFragment : Fragment(), GalleryAdapter.OnGalleryItemClickListener {

    private lateinit var binding: FragmentPhotosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPhotosBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPhotosRcView()
    }

    private fun initPhotosRcView(){
        binding.photosRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        val photoFiles: List<String> = ItemGalleryModel.findImagesFiles(requireContext())

        binding.photosRecyclerView.adapter =
            GalleryAdapter(requireActivity(), photoFiles , this)
    }

    override fun onItemCLicked(imgUri: String) {
        val intent = Intent(requireActivity(), GalleryEditorActivity::class.java)
        intent.putExtra(GalleryActivity.IMG, imgUri)
        startActivity(intent)
    }
}