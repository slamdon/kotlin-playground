package devdon.com.imagelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import devdon.com.imagelist.model.ImageModel
import kotlinx.android.synthetic.main.layout_image_list_item.view.*

/**
 * Created by slamdon on 2017/11/24.
 */

class ImageListAdapter(val feedModelItems: List<ImageModel>) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    // 入口
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 指定了 layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_list_item, parent, false)
        return ViewHolder(view)
    }

    // 綁定資料
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindImageModel( feedModelItems[position] )
    }

    // 返回數目
    override fun getItemCount(): Int {
        return feedModelItems.size
    }

    // view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindImageModel(imageModel: ImageModel){
            // set description
            itemView.descriptionTextView.text = imageModel.description

            // set image
            when(imageModel.imageName){
                "img_1" -> itemView.imageView.setImageResource(R.drawable.img_1)
                "img_2" -> itemView.imageView.setImageResource(R.drawable.img_2)
                "img_3" -> itemView.imageView.setImageResource(R.drawable.img_3)
                "img_4" -> itemView.imageView.setImageResource(R.drawable.img_4)
                "img_5" -> itemView.imageView.setImageResource(R.drawable.img_5)
                "img_6" -> itemView.imageView.setImageResource(R.drawable.img_6)
                "img_7" -> itemView.imageView.setImageResource(R.drawable.img_7)
                "img_8" -> itemView.imageView.setImageResource(R.drawable.img_8)
                "img_9" -> itemView.imageView.setImageResource(R.drawable.img_9)
                "img_10" -> itemView.imageView.setImageResource(R.drawable.img_10)
                "img_11" -> itemView.imageView.setImageResource(R.drawable.img_11)
                "img_12" -> itemView.imageView.setImageResource(R.drawable.img_12)
                "img_13" -> itemView.imageView.setImageResource(R.drawable.img_13)
                "img_14" -> itemView.imageView.setImageResource(R.drawable.img_14)
                "img_15" -> itemView.imageView.setImageResource(R.drawable.img_15)
            }

        }
    }

}