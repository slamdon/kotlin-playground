package devdon.com.pulltorequest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var products = ArrayList<ProductModel>()

    lateinit var productsAdapter:ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupView()
    }

    private fun setupView() {

        // setup data
        products.add(ProductModel("Product 1"))

        productsAdapter = ProductsAdapter(products)

        // setup recyclerView
        productRecyclerView.layoutManager = GridLayoutManager(this,2)
        productRecyclerView.adapter = productsAdapter

        // setup RefreshLayout
        productsRefreshLayout.setProgressViewOffset(true,50,100)

        // size
        productsRefreshLayout.setSize(SwipeRefreshLayout.LARGE)

        // 啟動下拉刷新
        productsRefreshLayout.isEnabled = true

        // 監聽下拉刷新
        productsRefreshLayout.setOnRefreshListener(productsRefreshListener)

    }

    // 下拉之後隨即改變 RecyclerView 中的內容
    private val productsRefreshListener = SwipeRefreshLayout.OnRefreshListener{
        // 模擬加載時間
        Thread.sleep(200)

        val newProducts = ArrayList<ProductModel>()

        // 隨即增加內容
        for(i in 0..10){
            val randomNumber = Random().nextInt(100)
            newProducts.add(ProductModel("Product $randomNumber"))
        }
        productsAdapter.products = newProducts

        // 刷新畫面
        productsAdapter.notifyDataSetChanged()

        // 停止下拉動畫
        productsRefreshLayout.isRefreshing = false
    }

}
