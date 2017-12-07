package devdon.com.sidemenu

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
        goButton.setOnClickListener(goButtonClickHandler)
    }

    private val goButtonClickHandler = View.OnClickListener{ view ->
        val intent = Intent(this,DetailActivity::class.java)
        startActivity(intent)

    }


}
