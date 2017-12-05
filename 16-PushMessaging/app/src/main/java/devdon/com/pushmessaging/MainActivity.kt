package devdon.com.pushmessaging

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AlertDialog
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var messageReceiver = object:BroadcastReceiver(){

        override fun onReceive(p0: Context?, p1: Intent?) {
            val text = p1?.getStringExtra("message")
            val simpleAlert = AlertDialog.Builder(this@MainActivity).create()
            simpleAlert.setTitle("Notificaiton")
            simpleAlert.setMessage(text)

            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
                dialogInterface, i ->
                Toast.makeText(applicationContext, "You clicked on OK", Toast.LENGTH_SHORT).show()
            })

            simpleAlert.show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, IntentFilter("DonMessage"))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)
    }

}
