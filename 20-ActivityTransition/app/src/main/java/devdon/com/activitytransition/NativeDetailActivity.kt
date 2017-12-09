package devdon.com.activitytransition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window


class NativeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setTransition 必須早於 setContentView
        setupTrainsition()

        setContentView(R.layout.activity_native_detail)


    }

    private fun setupTrainsition() {
        // 在這裡執行 requestFeature 或者在 /values/styles 中加入
        // window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        when(intent.getStringExtra("flag")){
            "explode" -> {
                val explodeTransition = Explode()
                explodeTransition.duration = 1000
                window.enterTransition = explodeTransition
                window.exitTransition = explodeTransition
            }

            "slide" -> {
                val slideTransition = Slide()
                slideTransition.duration = 1000
                window.enterTransition = slideTransition
                window.exitTransition = slideTransition
            }

            "fade" -> {
                val fadeTransition = Fade()
                fadeTransition.duration = 1000
                window.enterTransition = fadeTransition
                window.exitTransition = fadeTransition

            }
        }

    }




}
