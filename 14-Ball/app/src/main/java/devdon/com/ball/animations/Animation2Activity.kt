package devdon.com.ball.animations

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import devdon.com.ball.R
import kotlinx.android.synthetic.main.activity_animation2.*

class Animation2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation2)

        setupView()
    }

    private fun setupView() {
        title = "ObjectAnimator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        button6.setOnClickListener(goButtonClickHandler)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private val goButtonClickHandler = View.OnClickListener { view ->
        val animator = ObjectAnimator.ofFloat(donImageView, "rotationY", 0.0f, 360.0f)
        animator.duration = 600
        animator.start()

    }

}