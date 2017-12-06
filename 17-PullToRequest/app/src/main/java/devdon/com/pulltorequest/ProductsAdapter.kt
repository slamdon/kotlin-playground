package devdon.com.pulltorequest

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_product_item.view.*

/**
 * Created by slamdon on 2017/12/5.
 */

class ProductsAdapter(var products: ArrayList<ProductModel>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    // 入口
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // 指定了 layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)

        val metrics = parent.context.resources.displayMetrics

        Log.e("log","${metrics.widthPixels}")
        view.minimumWidth = 525 * (1080 / metrics.widthPixels)
        view.minimumHeight = 150

        return ViewHolder(view)
    }

    // 綁定資料
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindInformation(position, products[position])
    }

    // 返回數目
    override fun getItemCount(): Int {
        return products.size
    }

    // view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindInformation(position: Int, product: ProductModel){
            itemView.nameTextView.text = product.name
        }
    }

}