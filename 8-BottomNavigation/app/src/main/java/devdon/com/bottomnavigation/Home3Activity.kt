package devdon.com.bottomnavigation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_home3.*
import android.support.v4.content.IntentCompat



class Home3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home3)

        title = "Home 3"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        homeButton.setOnClickListener(homeButtonHandler)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    private val homeButtonHandler = View.OnClickListener { view ->
        val intentHomeActivity = Intent(this, MainActivity::class.java)
        intentHomeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or IntentCompat.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intentHomeActivity)

    }


}
