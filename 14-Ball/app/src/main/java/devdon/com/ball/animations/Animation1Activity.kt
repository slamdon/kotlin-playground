package devdon.com.ball.animations

import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import devdon.com.ball.R
import kotlinx.android.synthetic.main.activity_animation1.*

class Animation1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation1)

        setupView()
    }

    private fun setupView() {
        title = "ValueAnimator"
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
        val animator = ValueAnimator.ofFloat(0f, -800f, 0f)
        animator.duration = 600

        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator?.animatedValue as Float
            donImageView.translationY = value
        }

        animator.start()
    }

}
