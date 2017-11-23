package devdon.com.scalableimageview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event?.actionMasked)
        {
            MotionEvent.ACTION_DOWN -> {
                println("action down")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                println("pointer down")
            }
            MotionEvent.ACTION_MOVE -> {
                println("action move")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                println("pointer up")
            }
            MotionEvent.ACTION_UP -> {
                println("action up")
            }
        }
        return super.onTouchEvent(event)
    }


}
