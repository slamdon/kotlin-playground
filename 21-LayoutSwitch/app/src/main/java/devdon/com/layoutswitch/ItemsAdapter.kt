package devdon.com.layoutswitch


import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by slamdon on 2017/12/10.
 */

class ItemsAdapter(val items: List<ItemModel>, val layoutManager:GridLayoutManager) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    val VIEW_TYPE_SMALL = 1
    val VIEW_TYPE_BIG = 2

    override fun getItemViewType(position: Int): Int {
        val spanCount = layoutManager.spanCount

        when(spanCount){
            2 -> return VIEW_TYPE_SMALL
            else -> return VIEW_TYPE_BIG
        }
    }

    // 入口
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val metrics = parent.context.resources.displayMetrics


        // 指定了 layout
        when(viewType){
            VIEW_TYPE_SMALL -> {
                println("create view holder small")
                val view =  LayoutInflater.from(parent.context).inflate(R.layout.layout_item_small, parent, false)
                view.minimumWidth = 900 * (1080 / metrics.widthPixels)
                view.minimumHeight = 220
                return ViewHolder(view, viewType)
            }

            else -> {
                println("create view holder big")
                val view =  LayoutInflater.from(parent.context).inflate(R.layout.layout_item_big, parent, false)
                view.minimumWidth =  metrics.widthPixels - 16
                view.minimumHeight = 220
                return ViewHolder(view, viewType)
            }

        }

    }

    // 綁定資料
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = items[position]
        holder?.bindModel(item)
    }

    // 返回數目
    override fun getItemCount(): Int { return items.size }

    // view
    inner class ViewHolder(itemView: View, var viewType:Int) : RecyclerView.ViewHolder(itemView){
        var imageView: ImageView? = null
        var nameTextView: TextView? = null
        var likeTextView: TextView? = null
        var commentTextView: TextView? = null

        fun bindModel(item:ItemModel){
            // set description
            when(viewType){
                VIEW_TYPE_SMALL -> {
                    imageView = itemView.findViewById(R.id.smallImageView)
                    nameTextView = itemView.findViewById(R.id.smallNameTextView)
                }

                else -> {
                    imageView = itemView.findViewById(R.id.bigNameImageView)
                    nameTextView = itemView.findViewById(R.id.bigNameTextView)
                    likeTextView = itemView.findViewById(R.id.likeTextView)
                    commentTextView = itemView.findViewById(R.id.commentTextView)

                }
            }

            imageView?.setImageResource(item.image)
            nameTextView?.setText(item.name)
            likeTextView?.setText("Likes: ${item.likeCount}")
            commentTextView?.setText("comments: ${item.commentCount}")

        }

    }


}