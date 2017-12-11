package devdon.com.localdatabase

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_item.view.*

/**
 * Created by slamdon on 2017/12/11.
 */

class ItemsAdapter(var items: ArrayList<ItemModel>): RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindModel(items[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindModel(item:ItemModel, position:Int) {
            itemView.layout_item_number_textView.text = "$position"
            itemView.layout_item_name_textView.text = item.name
        }
    }

}