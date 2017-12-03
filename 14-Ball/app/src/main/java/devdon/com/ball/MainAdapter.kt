package devdon.com.ball

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_main.view.*
import java.util.*

/**
 * Created by slamdon on 2017/12/3.
 */

class MainAdapter(val clickListener:(position:Int) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    val items:ArrayList<String> = arrayListOf("ValueAnimator", "ObjectAnimator", "AnimatorSet", "Interpolator")

    // 入口
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 指定了 layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    // 綁定資料
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView?.setOnClickListener{ clickListener(position) }
        holder?.bindInformation(position, items[position])
    }

    // 返回數目
    override fun getItemCount(): Int {
        return items.size
    }

    // view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindInformation(position: Int, description: String){
            itemView.positionTextView.text = "$position"
            itemView.descriptionTextView.text = description

        }
    }
}