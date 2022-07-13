package xyz.lckyswmrs.testgalleryapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.lckyswmrs.testgalleryapp.R

class GalleryAdapter(
    private val mActivity: Activity,
    private val mFileList: List<String>,
    private val listener: OnGalleryItemClickListener
) :
    RecyclerView.Adapter<GalleryAdapter.CustomViewHolder?>() {

    interface OnGalleryItemClickListener {
        fun onItemCLicked(imgUri: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return CustomViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val imageUri = mFileList[position]

        Glide
            .with(mActivity)
            .load(imageUri)
            .override(200, 200)
            .centerCrop()
            .into(holder.imageResource)

        holder.imageResource.setOnClickListener {
            listener.onItemCLicked(imageUri)
        }
    }

    override fun getItemCount(): Int {
        return mFileList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageResource: ImageView

        init {
            imageResource =
                itemView.findViewById<View>(R.id.image_gallery_recycler_item) as ImageView
        }
    }
}
