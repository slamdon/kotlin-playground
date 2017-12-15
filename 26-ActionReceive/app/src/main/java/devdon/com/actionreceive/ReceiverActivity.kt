package devdon.com.actionreceive

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_receiver.*

class ReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)

        checkIntent()
    }

    private fun checkIntent() {

        if(Intent.ACTION_SEND.equals(intent.action) && intent.type != null){
            if("text/plain".equals(intent.type)){
                receiveTextHandler(intent)
            }
        }
    }

    private fun receiveTextHandler(intent:Intent) {
        val text = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(!text.isEmpty()){
            layout_receiver_received_textView.text = "$ $text"
        }
    }
}
