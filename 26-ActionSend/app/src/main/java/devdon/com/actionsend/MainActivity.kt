package devdon.com.actionsend

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        layout_main_shareButton.setOnClickListener(shareButtonClickHandler)
    }

    private var shareButtonClickHandler = View.OnClickListener { view ->
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, layout_main_shareEditText.text.toString())
        intent.type = "text/plain"
        startActivity(intent)
    }

}
