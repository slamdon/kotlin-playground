package devdon.com.pushmessaging

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessageService : FirebaseMessagingService() {

    lateinit var broadcast:LocalBroadcastManager

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)

        broadcast = LocalBroadcastManager.getInstance(this)

        Log.e("firebase message", message?.notification?.body)

        var contentText = ""
        if (message?.notification?.body != null) contentText = message.notification.body.toString()

        sendNotification(contentText)
        showAlert(contentText)
    }

    private fun sendNotification(message:String) {
        val notification = NotificationCompat.Builder(this,"channel id test")
                .setSmallIcon(R.drawable.icon_don_s)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon_don))
                .setContentTitle("Notification from Firebase")
                .setContentText(message)
                .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)

    }

    private fun showAlert(message:String) {
        val intent = Intent("DonMessage")
        intent.putExtra("message", message)
        broadcast.sendBroadcast(intent)
    }

}
