package devdon.com.bottomnavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.content.Intent
import android.support.v4.app.TaskStackBuilder
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_home2.*


class Home2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        title = "Home 2"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        homeButton.setOnClickListener(homeButtonOnClickHandler)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private var homeButtonOnClickHandler = View.OnClickListener { view ->
        val intentHomeDetailActivity = Intent(this, Home3Activity::class.java)
        startActivity(intentHomeDetailActivity)
    }

}
