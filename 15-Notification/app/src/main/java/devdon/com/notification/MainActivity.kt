package devdon.com.notification

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        sendButton.setOnClickListener(sendButtonClickHandler)
    }

    private val sendButtonClickHandler = View.OnClickListener { view ->

        val notification = NotificationCompat.Builder(this,"channel id test")
                .setSmallIcon(R.drawable.icon_don_s)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon_don))
                .setContentTitle("Notification from Don")
                .setContentText("It's time to go")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(longArrayOf(300, 600, 300, 600))
                .setLights(Color.RED, 1000, 1000)
                .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }


}
