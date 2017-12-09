package devdon.com.activitytransition

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Pair

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }


    private fun setupView() {
        explodeButton.setOnClickListener(explodeButtonClickHandler)
        slideButton.setOnClickListener(slideButtonClickHandler)
        fadeButton.setOnClickListener(fadeButtonClickHandler)
        loveImageView.setOnClickListener(loveImageViewClickHandler)
    }

    private var explodeButtonClickHandler = View.OnClickListener { view ->
        startTransitionWithFlag("explode")
    }

    private var slideButtonClickHandler = View.OnClickListener { view ->
        startTransitionWithFlag("slide")
    }

    private var fadeButtonClickHandler = View.OnClickListener { view ->
        startTransitionWithFlag("fade")
    }

    private fun startTransitionWithFlag(flag:String) {
        val intent = Intent(this, NativeDetailActivity::class.java)
        intent.putExtra("flag", flag)
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    }

    private var loveImageViewClickHandler = View.OnClickListener { view ->
        val intent = Intent(this, NativeDetailActivity::class.java)
        val sharedView1 = catImageView as View
        val sharedView2 = loveImageView as View

        val p1 = Pair(sharedView1, "catTransition")
        val p2 = Pair(sharedView2, "textTransition")
        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, p1, p2)

        startActivity(intent, transitionActivityOptions.toBundle())
    }


}
