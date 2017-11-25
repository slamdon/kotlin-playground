package devdon.com.imagelist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import devdon.com.imagelist.model.ImageListModel
import devdon.com.imagelist.model.ImageModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val imageList by lazy {
        listOf(
                ImageModel("img_1","This is a photo of 1"),
                ImageModel("img_2","This is a photo of 2"),
                ImageModel("img_3","This is a photo of 3"),
                ImageModel("img_4","This is a photo of 4"),
                ImageModel("img_5","This is a photo of 5"),
                ImageModel("img_6","This is a photo of 6"),
                ImageModel("img_7","This is a photo of 7"),
                ImageModel("img_8","This is a photo of 8"),
                ImageModel("img_9","This is a photo of 9"),
                ImageModel("img_10","This is a photo of 10"),
                ImageModel("img_11","This is a photo of 11"),
                ImageModel("img_12","This is a photo of 12"),
                ImageModel("img_13","This is a photo of 13"),
                ImageModel("img_14","This is a photo of 14"),
                ImageModel("img_15","This is a photo of 15")
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Image List"

        imageListRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ImageListAdapter(imageList)
        imageListRecyclerView.adapter = adapter


    }



}