package devdon.com.recorder

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
        playerButton.setOnClickListener(playerButtonClickHandler)
        recorderButton.setOnClickListener(recorderButtonClickHandler)
    }

    private val playerButtonClickHandler = View.OnClickListener { view ->
        val intent = Intent(this, PlayerActivity::class.java)
        startActivity(intent)
    }

    private val recorderButtonClickHandler = View.OnClickListener { view ->
        val intent = Intent(this,RecordActivity::class.java)
        startActivity(intent)
    }





}
