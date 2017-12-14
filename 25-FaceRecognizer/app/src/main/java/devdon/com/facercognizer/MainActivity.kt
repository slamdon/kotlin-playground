package devdon.com.facercognizer

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val OPEN_PHOTO_FOLDER_REQUEST_CODE = 1004
    private lateinit var faceView:FaceView

    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("onActivityResult requestCode is $requestCode")

        when(requestCode){
            OPEN_PHOTO_FOLDER_REQUEST_CODE -> {
                val imageUri = data?.data
                println("imageUri is $imageUri")
                if(imageUri != null){
                    faceView.setupWithImage(imageUri)
                } else {
                    Toast.makeText(this,"get image Uri failed", Toast.LENGTH_SHORT).show()
                }
            }

            else -> {
                println("no handler on ActivityResult, resultcode is $resultCode")
            }
        }

    }

    private fun setupView() {
        layout_main_album_button.setOnClickListener(albumButtonClickHandler)
        layout_main_detect_button.setOnClickListener(detectButtonClickHandler)

        faceView = findViewById(R.id.layout_face_view)
    }

    private var albumButtonClickHandler = View.OnClickListener { view ->
        takeImageFromAlbum()
    }

    private var detectButtonClickHandler = View.OnClickListener { view ->
        faceView.detectFace()
    }

    private fun takeImageFromAlbum() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, OPEN_PHOTO_FOLDER_REQUEST_CODE)

    }

}
